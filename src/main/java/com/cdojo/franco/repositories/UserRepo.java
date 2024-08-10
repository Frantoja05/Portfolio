package com.cdojo.franco.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdojo.franco.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>  {
	
	User findByEmail(String email);
	
	List<User> findAll();

}
