package com.ashraf.library.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashraf.library.dao.UserRepository;
import com.ashraf.library.entity.User;
import com.ashraf.library.entity.Book;
import com.ashraf.library.entity.Genre;

@Service
public class UserServiceImpl implements  UserService, UserDetailsService {

	private UserRepository userRepo;
	
	// inject with the constructor
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@Transactional
	public User findByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
		
		return userRepo.findByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User save(User admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User findUser(int id) {
		
		Optional<User> result = this.userRepo.findById(id);
		User user = null;
		if(result.isPresent()) {
			user = result.get();
		} else {
			throw new RuntimeException("Did not find book id "+id);
		}
		return user;	}

	@Override
	@Transactional
	public String deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	
}
