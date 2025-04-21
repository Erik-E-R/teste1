package com.example.teste1.controller;

import com.example.teste1.dto.MoradorRequestDTO;
import com.example.teste1.dto.MoradorResponseDTO;
import com.example.teste1.model.Morador;
import com.example.teste1.service.MoradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moradores")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @GetMapping
    public List<MoradorResponseDTO> listarTodosMoradores() {
        return moradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradorResponseDTO> buscarMoradorPorId(@PathVariable String id) {
        try {
            MoradorResponseDTO morador = moradorService.buscarPorId(id);
            return ResponseEntity.ok(morador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MoradorResponseDTO> criarMorador(@Valid @RequestBody MoradorRequestDTO moradorDTO) {
        MoradorResponseDTO moradorCriado = moradorService.salvar(moradorDTO);
        return ResponseEntity.ok(moradorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoradorResponseDTO> atualizarMorador(@PathVariable String id, @Valid @RequestBody MoradorRequestDTO moradorDTO) {
        try {
            MoradorResponseDTO moradorAtualizado = moradorService.atualizarMorador(id, moradorDTO);
            return ResponseEntity.ok(moradorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMorador(@PathVariable String id) {
        try {
            moradorService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
