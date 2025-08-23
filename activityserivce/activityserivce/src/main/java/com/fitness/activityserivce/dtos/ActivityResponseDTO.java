package com.fitness.activityserivce.dtos;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fitness.activityserivce.model.Activitytype;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ActivityResponseDTO {
	private String id;
	private String userId;
	private Activitytype type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	private Map<String, Object> additionalMetrics;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
}
