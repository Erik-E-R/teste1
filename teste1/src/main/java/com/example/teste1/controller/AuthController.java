package com.example.teste1.controller;

import com.example.teste1.config.security.JwtService;
import com.example.teste1.model.Morador;
import com.example.teste1.model.UsuarioRole;
import com.example.teste1.repository.MoradorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MoradorRepository moradorRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder; // ✅ Injeção do PasswordEncoder

    public AuthController(MoradorRepository moradorRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.moradorRepository = moradorRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder; // ✅ Adicionando ao construtor
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody Morador loginRequest) {
        Morador morador = moradorRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica a senha com PasswordEncoder
        if (!passwordEncoder.matches(loginRequest.getSenha(), morador.getSenha())) {
            return ResponseEntity.status(401).body("Senha inválida");
        }

        // Gera o token
        String token = jwtService.generateToken(morador.getEmail(), morador.getRole());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Morador novoMorador) {
        // Verifica se o e-mail já existe
        if (moradorRepository.findByEmail(novoMorador.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado.");
        }

        // Criptografa a senha antes de salvar
        novoMorador.setSenha(passwordEncoder.encode(novoMorador.getSenha()));

        // Define um ADMIN ou USER (Padrão: USER se não for especificado)
        if (novoMorador.getRole() == null) {
            novoMorador.setRole(UsuarioRole.USER);
        }

        moradorRepository.save(novoMorador);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
