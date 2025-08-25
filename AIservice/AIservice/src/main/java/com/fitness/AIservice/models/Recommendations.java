package com.fitness.AIservice.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recommendations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String activityId;
	private String userId;
	private String activityType;
	private String recommendation;
	private List<String> suggestion;
	private List<String> safety;
	@CreationTimestamp
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
