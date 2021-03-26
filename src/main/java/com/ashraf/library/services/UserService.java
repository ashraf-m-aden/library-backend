package com.ashraf.library.services;

import java.util.List;

import com.ashraf.library.entity.User;
import com.ashraf.library.entity.Book;

public interface UserService {

	public List<User> findAll();
	public User save(User admin);
	public User findUser(int id);
	public String deleteUser(int id);
	public User findByUsernameAndPassword(String pseudo, String password);	

}
