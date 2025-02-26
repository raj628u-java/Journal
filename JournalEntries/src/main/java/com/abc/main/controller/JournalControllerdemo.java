package com.abc.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalControllerdemo {
	
	private Map<Long, JournalEntries> journalEntries= new HashMap<>();
	
	@GetMapping("/getAllJournals")
	public List<JournalEntries> getAll() {
		return new ArrayList<>(journalEntries.values());
	}
	
	@PostMapping("/createJournal")
	public boolean createJournal(@RequestBody JournalEntries entry ) {
		//journalEntries.put(entry.getId(), entry);
		return true;
	}
	
	@GetMapping("/getAllJournalsById/{id}")
	public JournalEntries getJournalsById(@PathVariable Long id) {
		return journalEntries.get(id);
	}
	
	@PutMapping("/updateJournal/{id}")
	public JournalEntries updateJournalsById(@PathVariable Long id, @RequestBody JournalEntries entry) {
		return journalEntries.put(id, entry);
	}
	
}