package com.inda.trafficbuz.service;

import java.util.List;

import com.inda.trafficbuz.entity.User;

public interface UserService {
	public List<User>fetchAll() ;

	public User createUser(User user);

}
