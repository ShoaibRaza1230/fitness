package com.fitness.activityserivce.services;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
	
	@Autowired
	private WebClient userServiceWebClient;
	
	public boolean validateUser(String userId)
	{
		log.info("Calling user validation api for use validation");
		
		try {
			
			return userServiceWebClient.get().uri("users/{userId}/validate",userId)
					.retrieve()
					.bodyToMono(Boolean.class)
					.block();
		}catch(WebClientResponseException ex)
		{
			 
			if(ex.getStatusCode() == HttpStatus.NOT_FOUND )
			{
				throw new RuntimeException("User Not Found: "+userId);
			}
			else if(ex.getStatusCode() == HttpStatus.BAD_REQUEST )
			{
				throw new RuntimeException("Invalid Request: "+userId);
			}
			else
			{
				throw new RuntimeException("Invalid Request: "+userId);

			}
		}
		
	}
}
