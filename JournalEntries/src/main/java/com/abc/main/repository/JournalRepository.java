package com.abc.main.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.abc.main.controller.JournalEntries;

public interface JournalRepository extends MongoRepository<JournalEntries, ObjectId>{

}
