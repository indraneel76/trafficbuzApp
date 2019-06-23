package com.inda.trafficbuz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inda.trafficbuz.entity.User;
import com.inda.trafficbuz.repository.UserRepository;
import com.inda.trafficbuz.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public String  get()
	{
		return userService.fetchAll().toString();
		 
	}

}
