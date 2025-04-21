package com.example.teste1.dto;
import com.example.teste1.model.Morador;

public record MoradorResponseDTO(
        String id,
        String nome,
        String email,
        String bairro
) {
    public MoradorResponseDTO(Morador morador) {
        this(morador.getId(),
                morador.getNome(),
                morador.getEmail(),
                morador.getBairro());
    }
}
