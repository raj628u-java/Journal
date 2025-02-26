package com.abc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.main.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired 
	private UserService service;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserEntity> createJournal(@RequestBody UserEntity entry ) {	
		
		try {
			service.saveEntry(entry);
			return new ResponseEntity<>(entry,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}		

	}
}
