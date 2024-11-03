package com.enjoytrip.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.enjoytrip.auth.domain.AuthClaims;
import com.enjoytrip.jwt.config.JwtProperties;
import com.enjoytrip.jwt.exception.TokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.LinkedHashMap;

import static com.enjoytrip.common.exception.ExceptionCode.INVALID_TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private String tokenPrefix = "Bearer ";
    private final ObjectMapper objectMapper;

    public boolean isIncludeTokenPrefix(String header) {
        return header.split(" ")[0].equals(tokenPrefix.trim());
    }

    public String extractTokenFromHeader(String header) {
        if (header.startsWith(tokenPrefix)) {
            return header.replace(tokenPrefix, "").trim();
        }
        throw new TokenException(INVALID_TOKEN_PREFIX);
    }

    public String createToken(AuthClaims authClaims, Instant currentDate) {
        return JWT.create()
                .withSubject(String.valueOf(authClaims.getUserId()))
                .withExpiresAt(currentDate.plusSeconds(jwtProperties.getTokenValidityInSeconds()))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }

    public boolean isTokenExpired(String token) {
        Instant expiredAt = JWT.require(Algorithm.HMAC512(jwtProperties.getSecret()))
                .build().verify(token)
                .getExpiresAtAsInstant();
        return expiredAt.isBefore(Instant.now());
    }

    public boolean isTokenNotManipulated(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtProperties.getSecret()))
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AuthClaims extractClaimFromToken(String token) {
        String payload = JWT.decode(token)
                .getPayload();

        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decodedBytes);

        return parseUserFromJwt(decodedPayload);
    }

    private AuthClaims parseUserFromJwt(String decodedPayload) {
        try {
            LinkedHashMap<String, Object> payloadMap = objectMapper.readValue(decodedPayload, LinkedHashMap.class);
            Long userId = Long.parseLong((String) payloadMap.get("sub"));
            return new AuthClaims(userId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public Long extractSubjectId(String authorizationParameters) {
        String payload = JWT.decode(authorizationParameters)
                .getPayload();

        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decodedBytes);

        try {
            LinkedHashMap<String, Object> payloadMap = objectMapper.readValue(decodedPayload, LinkedHashMap.class);
            return Long.parseLong((String) payloadMap.get("sub"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
