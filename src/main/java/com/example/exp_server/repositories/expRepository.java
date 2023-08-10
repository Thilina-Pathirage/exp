package com.example.exp_server.repositories;

import com.example.exp_server.models.exp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface expRepository extends MongoRepository<exp, String> {
}
