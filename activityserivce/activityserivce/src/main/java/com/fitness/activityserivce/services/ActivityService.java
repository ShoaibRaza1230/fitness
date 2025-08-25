package com.fitness.activityserivce.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Service;

import com.fitness.activityserivce.dtos.ActivityRequestDTO;
import com.fitness.activityserivce.dtos.ActivityResponseDTO;
import com.fitness.activityserivce.model.Activity;
import com.fitness.activityserivce.model.Activitytype;
import com.fitness.activityserivce.repositries.ActivityRepository;
import com.netflix.discovery.converters.Auto;

import lombok.extern.slf4j.Slf4j;
 

@Service
@Slf4j
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepo;
	 
	@Autowired
	private UserValidationService userValidationService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${spring.exchange.name}")
	private String exchange;
	
	@Value("${spring.routing.key}")
	private String routingKey;
	
	public ActivityResponseDTO trackActivity(ActivityRequestDTO requestDto)
	{
		boolean isValidUser = userValidationService.validateUser(requestDto.getUserId());
		
		if(!isValidUser)
		{
			throw new RuntimeException("User Not Found: "+requestDto.getUserId());
		}
		Activity activity = Activity.builder()
				.userId(requestDto.getUserId())
				.type(requestDto.getType())
				.duration(requestDto.getDuration())
				.caloriesBurned(requestDto.getCaloriesBurned())
				.startTime(requestDto.getStartTime())
				.additionalMetrics(requestDto.getAdditionalMetrics())
				.build();
		Activity saveActivity = activityRepo.save(activity);
		
		//publish to RabbitMQ
		try {
			rabbitTemplate.convertAndSend(exchange, routingKey, saveActivity);
		}catch(Exception e)
		{
			log.error("Failed to publish activity on RabbitMq");
		}
		return mapToResponse(saveActivity);
}
	private ActivityResponseDTO mapToResponse(Activity activity)
	{
		ActivityResponseDTO response = new ActivityResponseDTO();
		response.setId(activity.getId());
		response.setUserId(activity.getUserId());
		response.setType(activity.getType());
		response.setDuration(activity.getDuration());
		response.setCaloriesBurned(activity.getCaloriesBurned());
		response.setStartTime(activity.getStartTime());
		response.setAdditionalMetrics(activity.getAdditionalMetrics());
		response.setCreateAt(activity.getCreateAt());
		response.setUpdateAt(activity.getUpdateAt());
		return response;
	}
	public List<ActivityResponseDTO> getUserActivities(String userId) {
		 List<Activity> activities = activityRepo.findByUserId(userId);
		 return activities.stream()
				 .map(this::mapToResponse)
				 .collect(Collectors.toList());
	}
	public ActivityResponseDTO getActivity(String activityId) {
		return activityRepo.findById(activityId)
				.map(this::mapToResponse)
				.orElseThrow(()-> new RuntimeException("Activity Not Found: "+activityId));
	 
	}
}