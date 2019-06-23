package com.inda.trafficbuz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inda.trafficbuz.entity.User;
import com.inda.trafficbuz.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User>fetchAll()
	{
	 List<User> users = userRepository.fetchAll();
	 System.out.println("returned users "+users);
	 return users;
	}
	

}
