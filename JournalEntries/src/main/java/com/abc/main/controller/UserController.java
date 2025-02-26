package com.abc.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.main.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers(){
		List<UserEntity> all = service.getAll();
		
		if(all!=null && !all.isEmpty()) {
			return new ResponseEntity<>(all.toString(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	@GetMapping("/getUserByUserName")
	public ResponseEntity<UserEntity> getJournalsById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<UserEntity> byId = Optional.ofNullable(service.findByuserName(userName));
		return byId.map(entry->new ResponseEntity<>(byId.get(), HttpStatus.OK)).orElseGet
				(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	//	if(byId.isPresent()) {
	//		return new ResponseEntity<>(byId.get(), HttpStatus.OK);
	//	}
	//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<?> updateJournalsById(@RequestBody UserEntity user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
	UserEntity userId =	service.findByuserName(userName);
	if (userId!=null) {
		userId.setUserName(user.getUserName());
		userId.setPassword(user.getPassword());
		service.saveEntry(userId);
		return new ResponseEntity<>(userId,HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}