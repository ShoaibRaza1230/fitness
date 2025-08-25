package com.fitness.AIservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.AIservice.models.Recommendations;
import com.fitness.AIservice.repositories.RecommendationRepository;

@Service
public class RecommendationService {

	@Autowired
	private RecommendationRepository recommendRepo;

	public List<Recommendations> getUserRecommendation(String userId) {
		 return recommendRepo.findByUserId(userId);
	}

	public Recommendations getactivityRecommendation(String activityId) {
		return recommendRepo.findByActivityId(activityId)
				.orElseThrow(()-> new RuntimeException("No recommendation found for this activity id: "+activityId));
	}
}
