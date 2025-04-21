package com.example.teste1.repository;


import com.example.teste1.model.Notificacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificacaoRepository extends MongoRepository<Notificacao, String> {
}
