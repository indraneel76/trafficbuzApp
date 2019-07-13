package com.inda.trafficbuz.repository;

import java.util.List;

import com.inda.trafficbuz.entity.User;

public interface UserRepository {
	
	public List<User> fetchAll() ;
	public void add(User user);

}
