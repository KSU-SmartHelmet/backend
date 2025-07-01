package com.example.SmartHelmet.repository;

import com.example.SmartHelmet.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserId(String userId);
}
