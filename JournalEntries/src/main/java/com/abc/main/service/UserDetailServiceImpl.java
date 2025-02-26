package com.abc.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.abc.main.controller.UserEntity;
import com.abc.main.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user = userrepo.findByuserName(username);
		if(user!=null) {
			UserDetails userDetails = User.builder()
			.username(user.getUserName())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			
		return userDetails;	
		}
		throw new UsernameNotFoundException("User noyt Found with username "+username);
	}

}
