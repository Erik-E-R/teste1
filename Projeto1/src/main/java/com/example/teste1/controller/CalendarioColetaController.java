package com.example.teste1.controller;

import com.example.teste1.dto.CalendarioColetaDTO;
import com.example.teste1.model.CalendarioColeta;
import com.example.teste1.service.CalendarioColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendarios")
public class CalendarioColetaController {

    @Autowired
    private CalendarioColetaService calendarioColetaService;

    @GetMapping
    public List<CalendarioColetaDTO> listarCalendarios() {
        return calendarioColetaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarioColetaDTO> getCalendarioById(@PathVariable String id) {
        Optional<CalendarioColetaDTO> calendario = calendarioColetaService.buscarPorId(id);
        return calendario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CalendarioColetaDTO createCalendario(@RequestBody CalendarioColeta calendarioColeta) {
        return calendarioColetaService.salvar(calendarioColeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarioColetaDTO> updateCalendario(@PathVariable String id, @RequestBody CalendarioColeta calendarioColeta) {
        CalendarioColetaDTO updatedCalendario = calendarioColetaService.atualizar(id, calendarioColeta);
        if (updatedCalendario != null) {
            return ResponseEntity.ok(updatedCalendario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendario(@PathVariable String id) {
        calendarioColetaService.excluir(id);
        return ResponseEntity.noContent().build();
    }




}
