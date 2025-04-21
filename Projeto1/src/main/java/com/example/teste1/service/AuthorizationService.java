package com.example.teste1.service;

import com.example.teste1.repository.MoradorRepository;
import com.example.teste1.model.Morador;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService implements UserDetailsService {


    private final MoradorRepository moradorRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthorizationService(MoradorRepository moradorRepository, PasswordEncoder passwordEncoder) {
        this.moradorRepository = moradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return moradorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }

    public void verificarSenha(String email, String senha) {
        Morador morador = moradorRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Email ou senha inválidos!"));

        if (!passwordEncoder.matches(senha, morador.getSenha())) {
            throw new BadCredentialsException("Email ou senha inválidos!");
        }
    }
}

