package com.dc3.repository;

import org.springframework.data.repository.CrudRepository;

import com.dc3.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findTop1ByEmail(String email);
	
	
}