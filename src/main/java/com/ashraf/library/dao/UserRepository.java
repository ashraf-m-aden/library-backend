package com.ashraf.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashraf.library.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsernameAndPassword(String pseudo, String password);	
}
