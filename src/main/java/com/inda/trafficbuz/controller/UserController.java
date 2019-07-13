	package com.inda.trafficbuz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inda.trafficbuz.entity.User;
import com.inda.trafficbuz.repository.UserRepositoryImp;
import com.inda.trafficbuz.service.UserService;
import com.inda.trafficbuz.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*
	@GetMapping("/users")
	public String  get()
	{
		return userService.fetchAll().toString();
		 
	}
	*/
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public @ResponseBody List<User> getUsers()
	{
		return userService.fetchAll();
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	
	

}
