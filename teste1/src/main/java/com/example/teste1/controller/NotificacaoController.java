package com.example.teste1.controller;

import com.example.teste1.dto.NotificacaoDTO;
import com.example.teste1.service.NotificacaoService;
import com.example.teste1.model.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public List<NotificacaoDTO> listarNotificacoes() {
        return notificacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> buscarNotificacaoPorId(@PathVariable String id) {
        return notificacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public NotificacaoDTO criarNotificacao(@RequestBody Notificacao notificacao) {
        return new NotificacaoDTO(notificacaoService.salvar(notificacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> atualizarNotificacao(@PathVariable String id, @RequestBody Notificacao notificacao) {
        return ResponseEntity.ok(new NotificacaoDTO(notificacaoService.atualizarNotificacao(id, notificacao)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable String id) {
        notificacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
