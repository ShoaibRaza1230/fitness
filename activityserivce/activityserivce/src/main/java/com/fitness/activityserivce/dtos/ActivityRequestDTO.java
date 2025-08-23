package com.fitness.activityserivce.dtos;

import java.time.LocalDateTime;
import java.util.Map;

import com.fitness.activityserivce.model.Activitytype;

import lombok.Data;

@Data
public class ActivityRequestDTO {
 
	private String userId;
	private Activitytype type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	private Map<String, Object> additionalMetrics;
	 
}