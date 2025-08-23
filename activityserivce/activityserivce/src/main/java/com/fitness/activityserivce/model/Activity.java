package com.fitness.activityserivce.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="acitivites")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String userId;
	private Activitytype type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	@JdbcTypeCode(SqlTypes.JSON)  // Hibernate 6+ handles Map<String, Object> as JSON
	@Column(columnDefinition = "json") // MySQL JSON column
	private Map<String, Object> additionalMetrics;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@UpdateTimestamp
	private LocalDateTime updateAt;
}
