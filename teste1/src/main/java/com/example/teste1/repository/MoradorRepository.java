package com.example.teste1.repository;

import com.example.teste1.model.Morador;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface MoradorRepository extends MongoRepository<Morador, String> {

    Optional<Morador> findByEmail(String email);

    }
