package com.abc.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.abc.main.controller.JournalEntries;
import com.abc.main.controller.UserEntity;
import com.abc.main.repository.JournalRepository;

@Component
public class JournalService {

	@Autowired
	private JournalRepository repo;
	
	@Autowired
	private UserService usersvc;
	
	@Transactional
	public void saveEntry(JournalEntries entry, String userName) {
		try {
			UserEntity userEntity = usersvc.findByuserName(userName);
			entry.setDate(LocalDateTime.now());
			JournalEntries savedEntry = repo.save(entry);
			userEntity.getJournalEntries().add(savedEntry);
			usersvc.saveEntry(userEntity);
		}catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("An error occured while saving" +e);		}
		
	}
	
	public List<JournalEntries> getAll() {
		return repo.findAll();
	}
	
	public Optional<JournalEntries> findById(ObjectId id) {
		return repo.findById(id);
	}

	public void saveEntry(JournalEntries myentry) {
		// TODO Auto-generated method stub
		repo.save(myentry);
		
	}
}
