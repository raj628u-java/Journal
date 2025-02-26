package com.abc.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection = "users")
@Data
public class UserEntity {
	
	@Id 
	private ObjectId id;
	
	@Indexed(unique=true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	
	private List<String> roles;
	
	@DBRef
	private List<JournalEntries> journalEntries= new ArrayList<>();

	
	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", journalEntries="
				+ journalEntries + "]";
	}


	public List<JournalEntries> getJournalEntries() {
		return journalEntries;
	}


	public void setJournalEntries(List<JournalEntries> journalEntries) {
		this.journalEntries = journalEntries;
	}
	
	
	
}
