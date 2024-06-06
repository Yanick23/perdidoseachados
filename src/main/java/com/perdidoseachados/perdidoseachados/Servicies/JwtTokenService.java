package com.perdidoseachados.perdidoseachados.Servicies;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P"; // Chave secreta utilizada para gerar e verificar o token

    private static final String ISSUER = "pizzurg-api"; // Emissor do token

    public String generateToken(Usuario usuario) {
        try {
            // Define o algoritmo HMAC SHA256 para criar a assinatura do token
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            // Crie um mapa para armazenar informações adicionais do usuário
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", usuario.getId()); // Adicione o ID do usuário
            userInfo.put("username", usuario.getUsername()); // Adicione o nome de usuário
            userInfo.put("email", usuario.getEmail()); // Adicione o email do usuário (se aplicável)

            System.out.println( JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withSubject(usuario.getUsername())
                    .withClaim("userInfo", userInfo) // Adicione o mapa de informações do usuário
                    .sign(algorithm));
            // Gere o token
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withSubject(usuario.getUsername())
                    .withClaim("userInfo", userInfo) // Adicione o mapa de informações do usuário
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER) // Define o emissor do token
                    .build()
                    .verify(token) // Verifica a validade do token
                    .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTVerificationException exception){
            throw new RuntimeException("TokenInvalido");
        }
    }
    private static Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("Africa/Maputo")).toInstant();
    }

    private static Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("Africa/Maputo")).plusHours(8).toInstant();
    }
}