package com.example.teste1.dto;

import com.example.teste1.model.Notificacao;
import java.util.Date;

public record NotificacaoDTO(
        String id,
        String idMorador,
        String idCalendario,
        Date dataEnvio,
        String status,
        String emailEnviado
) {
    public NotificacaoDTO(Notificacao notificacao) {
        this(
                notificacao.getIdNotificacao(),
                notificacao.getMorador().getId(),
                notificacao.getCalendario().getIdCalendario(),
                notificacao.getDataEnvio(),
                notificacao.getStatus(),
                notificacao.getEmailEnviado()
        );
    }
}

