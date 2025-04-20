package com.example.teste1.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.teste1.model.UsuarioRole;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("YourSecretKeyHereYourSecretKeyHere".getBytes());

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * Gera um token JWT para o usuário autenticado com a role */
    public String generateToken(String email, UsuarioRole role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name())  // Usa diretamente o Enum sem conversão extra
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrai um campo do token JWT */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrai o e-mail do token */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrai a role do token */
    public UsuarioRole extractRole(String token) {
        String roleString = extractClaim(token, claims -> claims.get("role", String.class));
        return UsuarioRole.valueOf(roleString); // Converte a String de volta para Enum
    }

    /**
     * Valida se o token ainda é válido */
    public boolean isTokenValid(String token, String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
