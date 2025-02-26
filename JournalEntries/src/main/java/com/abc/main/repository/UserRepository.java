package com.abc.main.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.abc.main.controller.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId>{

	UserEntity findByuserName(String UserName);
}
