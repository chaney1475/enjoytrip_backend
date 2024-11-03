package com.enjoytrip.jwt;

import com.enjoytrip.auth.domain.AuthClaims;
import com.enjoytrip.jwt.exception.TokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtService {
    private final JwtUtil jwtUtil;

    public AuthClaims getClaims(String token) {
        validateToken(token);
        return jwtUtil.extractClaimFromToken(token);
    }

    public String extractTokenFromHeader(String header) {
        return jwtUtil.extractTokenFromHeader(header);
    }

    private void validateToken(String token) {
        if (!jwtUtil.isTokenNotManipulated(token)) {
            throw new TokenException("INVALID_TOKEN", "로그인 실패", "다시 로그인 해주세요.");
        }

        if (jwtUtil.isTokenExpired(token)) {
            throw new TokenException("TOKEN_EXPIRED", "로그인 필요", "세션이 만료되었으므로 다시 로그인 해주세요.");
        }
    }
}
