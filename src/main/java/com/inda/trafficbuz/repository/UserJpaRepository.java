package com.inda.trafficbuz.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.inda.trafficbuz.entity.User;



@Repository
@RepositoryRestResource(path="mfgs", collectionResourceRel="mfgs")
public interface UserJpaRepository extends CrudRepository<User,String> {
	
	User findByUsername(String username);
	
}
