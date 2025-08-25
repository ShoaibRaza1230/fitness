package com.fitness.AIservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.AIservice.models.Recommendations;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendations, String> {

	List<Recommendations> findByUserId(String userId);
	Optional<Recommendations> findByActivityId(String activityId);
}
