package com.example.teste1.service;

import com.example.teste1.model.Morador;
import com.example.teste1.dto.*;
import com.example.teste1.repository.MoradorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MoradorResponseDTO salvar(MoradorRequestDTO moradorRequestDTO) {
        Morador morador = new Morador();
        BeanUtils.copyProperties(moradorRequestDTO, morador);
        morador.setSenha(passwordEncoder.encode(moradorRequestDTO.senha()));

        Morador moradorSalvo = moradorRepository.save(morador);
        return new MoradorResponseDTO(moradorSalvo);
    }

    public MoradorResponseDTO buscarPorId(String id) {
        Morador morador = moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado!"));
        return new MoradorResponseDTO(morador);
    }


    public List<MoradorResponseDTO> listarTodos() {
        return moradorRepository
                .findAll()
                .stream()
                .map(MoradorResponseDTO::new)
                .toList();
    }

    public MoradorResponseDTO atualizarMorador(String id, MoradorRequestDTO atualizarMorador) {
        Morador moradorExistente = moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado!"));

        moradorExistente.setNome(atualizarMorador.nome());
        moradorExistente.setEmail(atualizarMorador.email());
        moradorExistente.setBairro(atualizarMorador.bairro());

        if (!atualizarMorador.senha().isEmpty()) {
            moradorExistente.setSenha(passwordEncoder.encode(atualizarMorador.senha()));
        }

        Morador moradorAtualizado = moradorRepository.save(moradorExistente);
        return new MoradorResponseDTO(moradorAtualizado);
    }

    public void excluir(String id) {
        if (!moradorRepository.existsById(id)) {
            throw new RuntimeException("Morador não encontrado!");
        }
        moradorRepository.deleteById(id);
    }

}
