package com.example.teste1.repository;


import com.example.teste1.model.CalendarioColeta;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CalendarioColetaRepository extends MongoRepository<CalendarioColeta, String> {
}
