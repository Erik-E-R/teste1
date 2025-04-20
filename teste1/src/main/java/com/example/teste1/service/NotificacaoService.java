package com.example.teste1.service;

import com.example.teste1.dto.NotificacaoDTO;
import com.example.teste1.repository.NotificacaoRepository;
import com.example.teste1.model.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public Notificacao salvar(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public List<NotificacaoDTO> listarTodas() {
        return notificacaoRepository.findAll()
                .stream()
                .map(NotificacaoDTO::new)
                .toList();
    }

    public Optional<NotificacaoDTO> buscarPorId(String id) {
        return notificacaoRepository.findById(id)
                .map(NotificacaoDTO::new);
    }

    public Notificacao atualizarNotificacao(String id, Notificacao notificacao) {
        Notificacao notificacaoExistente = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada!"));
        notificacaoExistente.setDataEnvio(notificacao.getDataEnvio());
        notificacaoExistente.setStatus(notificacao.getStatus());
        notificacaoExistente.setEmailEnviado(notificacao.getEmailEnviado());
        return notificacaoRepository.save(notificacaoExistente);
    }

    public void excluir(String id) {
        notificacaoRepository.deleteById(id);
    }
}
