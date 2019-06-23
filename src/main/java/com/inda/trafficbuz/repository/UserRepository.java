package com.inda.trafficbuz.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inda.trafficbuz.entity.User;

@Repository
public class UserRepository {
	
	@PersistenceContext()
	private EntityManager entityManager;
	
	@Transactional
	public void add(User user)
	{
		entityManager.persist(user);
	}
	
	@Transactional
	public List<User> fetchAll()
	{
		return entityManager.createQuery("select u from User u").getResultList();
	}
	
	
	@PostConstruct
	public void setup() {
		System.out.println("setup called for userRepository");
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("destroy called for userRepository");
	}
	

}
