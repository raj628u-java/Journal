package com.abc.main.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.abc.main.controller.UserEntity;
import com.abc.main.repository.UserRepository;

@Component
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public void saveEntry(UserEntity entry) {
		entry.setPassword(passwordEncoder.encode(entry.getPassword()));
		entry.setRoles(Arrays.asList("User"));
		repo.save(entry);
	}
	
	public List<UserEntity> getAll() {
		return repo.findAll();
	}
	
	public UserEntity findByuserName(String userName) {
		return repo.findByuserName(userName);
	}
	
}
