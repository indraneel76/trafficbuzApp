package com.inda.trafficbuz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inda.trafficbuz.entity.User;
import com.inda.trafficbuz.repository.UserJpaRepository;
import com.inda.trafficbuz.repository.UserRepository;
import com.inda.trafficbuz.repository.UserRepositoryImp;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public List<User>fetchAll()
	{
	 List<User> users = (List<User>) userJpaRepository.findAll();
	 System.out.println("returned users "+users);
	 return users;
	}

	@Override
	public User createUser(User user) {
		userJpaRepository.save(user);
		return user;
	}
	

}
