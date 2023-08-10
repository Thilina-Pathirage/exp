package com.example.exp_server.repositories;

import com.example.exp_server.models.expSection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface expSectionRepository extends MongoRepository<expSection, String> {
}
