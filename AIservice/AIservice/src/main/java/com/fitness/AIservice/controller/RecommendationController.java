package com.fitness.AIservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.AIservice.models.Recommendations;
import com.fitness.AIservice.services.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

	@Autowired
	private RecommendationService recommendService;
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Recommendations>> getUserRecommendation(@PathVariable String userId)
	{
		return ResponseEntity.ok(recommendService.getUserRecommendation(userId));
	}
	
	@GetMapping("/activity/{activityId}")
	public ResponseEntity<Recommendations> getactivityRecommendation(@PathVariable String activityId)
	{
		return ResponseEntity.ok(recommendService.getactivityRecommendation(activityId));
	}
	
}
