package com.abc.main.controller;


import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.main.service.JournalService;
import com.abc.main.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalController {
		
		@Autowired
		private JournalService service;
		
		@Autowired
		private UserService usersvc;
		
		@GetMapping("/getAllJournalsByUserName/{userName}")
		public ResponseEntity<?> getAllJournalsByUserName(@PathVariable String userName) {
			UserEntity userEntity = usersvc.findByuserName(userName);
			List<JournalEntries> list = userEntity.getJournalEntries();
			if(list != null && !list.isEmpty()) {
			return new ResponseEntity<>(list.toString(),HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		@PostMapping("/createJournal/{userName}")
		public ResponseEntity<JournalEntries> createJournal(@PathVariable String userName,@RequestBody JournalEntries entry ) {
			
			try {
				service.saveEntry(entry,userName);
				return new ResponseEntity<>(entry,HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			

		}
		
		@GetMapping("/getAllJournalsById/{id}")
		public ResponseEntity<JournalEntries> getJournalsById(@PathVariable ObjectId id) {
			Optional<JournalEntries> byId = service.findById(id);
			return byId.map(entry->new ResponseEntity<>(byId.get(), HttpStatus.OK)).orElseGet
					(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
			
		//	if(byId.isPresent()) {
		//		return new ResponseEntity<>(byId.get(), HttpStatus.OK);
		//	}
		//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		}
		
		@PutMapping("/updateJournal/{userName}/{id}")
		public ResponseEntity<?> updateJournalsById(@PathVariable ObjectId id, 
				@PathVariable String userName,
				@RequestBody JournalEntries entry) {
			JournalEntries myentry = service.findById(id).orElse(null);
			if(myentry!=null) {
				myentry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")?entry.getTitle():myentry.getTitle());
				myentry.setContent(entry.getContent()!=null && !entry.getContent().equals("")?entry.getContent():myentry.getContent());
				service.saveEntry(myentry);
				return new ResponseEntity<>(myentry,HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}