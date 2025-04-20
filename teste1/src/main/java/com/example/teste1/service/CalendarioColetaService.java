package com.example.teste1.service;


import com.example.teste1.dto.CalendarioColetaDTO;
import com.example.teste1.model.CalendarioColeta;
import com.example.teste1.repository.CalendarioColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarioColetaService {

    @Autowired
    private CalendarioColetaRepository calendarioColetaRepository;

    public CalendarioColetaDTO salvar(CalendarioColeta calendarioColeta) {
        return new CalendarioColetaDTO(calendarioColetaRepository.save(calendarioColeta));
    }

    public List<CalendarioColetaDTO> listarTodos() {
        return calendarioColetaRepository.findAll().stream()
                .map(CalendarioColetaDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CalendarioColetaDTO> buscarPorId(String id) {
        Optional<CalendarioColeta> calendarioOptional = calendarioColetaRepository.findById(id);
        return calendarioOptional.map(CalendarioColetaDTO::new);
    }

    public void excluir(String id) {
        calendarioColetaRepository.deleteById(id);
    }

    public CalendarioColetaDTO atualizar(String id, CalendarioColeta calendarioColeta) {
        if (!calendarioColetaRepository.existsById(id)) {
            throw new RuntimeException("Calendário não encontrado!");
        }
        calendarioColeta.setIdCalendario(id);
        return new CalendarioColetaDTO(calendarioColetaRepository.save(calendarioColeta));
    }
}
