package com.inda.trafficbuz.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.inda.trafficbuz.entity.User;

public class RestController {
	
	@Test(timeout=3000)
	public void testFetchUsers() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> userResponse = restTemplate.exchange("http://localhost:8080/users", HttpMethod.GET,null, new ParameterizedTypeReference<List<User>>() {});
		List<User> users = userResponse.getBody();
		
		for (User user:users) {
			System.out.println("userid = "+user.getUsername() );
			System.out.println("=============");
		}
		
	}
	
	@Test(timeout=3000)
	public void createUser()
	{
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		user.setUsername("Pinku");
		user.setPassword("123456Test");
		User userRes =restTemplate.postForObject("http://localhost:8080/user", user, User.class);
		System.out.println("Response = "+userRes.getUsername()) ;
	}

}
