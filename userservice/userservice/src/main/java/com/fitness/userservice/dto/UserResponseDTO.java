package com.fitness.userservice.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fitness.userservice.model.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserResponseDTO {
	private String id;
	private String email;	
	private String firstName;
	private String lastName; 
	private String password;
	private LocalDateTime createdAt; 
	private LocalDateTime updatedAt;
}
