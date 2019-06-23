package com.inda.trafficbuz;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.inda.trafficbuz.entity.User;
import com.inda.trafficbuz.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(com.inda.trafficbuz.configuration.SpringJdbcConfiguration.class);
		UserRepository userRepository = (UserRepository)ctx.getBean(UserRepository.class);
		List<User> users = userRepository.fetchAll();
		
		for (User user : users)
		{
			System.out.println("userid " +user.getUserid());
			System.out.println("password " +user.getPassword());
			System.out.println("======================");
		}
		
	}

}
