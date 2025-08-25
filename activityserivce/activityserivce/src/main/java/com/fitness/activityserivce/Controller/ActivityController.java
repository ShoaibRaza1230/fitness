package com.fitness.activityserivce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.activityserivce.dtos.ActivityRequestDTO;
import com.fitness.activityserivce.dtos.ActivityResponseDTO;
import com.fitness.activityserivce.services.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	@PostMapping
	public ResponseEntity<ActivityResponseDTO> trackActivity(@RequestBody ActivityRequestDTO activityRequest)
	{
		return ResponseEntity.ok(activityService.trackActivity(activityRequest));
	}
	
	@GetMapping 
	public ResponseEntity<List<ActivityResponseDTO>> getUserActivities(@RequestHeader("X-User-ID") String userId)
	{
		return ResponseEntity.ok(activityService.getUserActivities(userId));

	}
	
	@GetMapping("/{activityId}")
	public ResponseEntity<ActivityResponseDTO > getActivity(@PathVariable String activityId)
	{
		return ResponseEntity.ok(activityService.getActivity(activityId));

	}
}
