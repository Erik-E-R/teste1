package com.example.teste1.dto;

import com.example.teste1.model.CalendarioColeta;
import java.util.Date;

public record CalendarioColetaDTO(
        String idCalendario,
        String bairro,
        Date dataColeta
) {
    public CalendarioColetaDTO(CalendarioColeta calendarioColeta) {
        this(calendarioColeta.getIdCalendario(),
                calendarioColeta.getBairro(),
                calendarioColeta.getDataColeta());
    }
}
