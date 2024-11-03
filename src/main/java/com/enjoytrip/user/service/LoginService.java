package com.enjoytrip.user.service;

import com.enjoytrip.auth.domain.AuthClaims;
import com.enjoytrip.common.exception.NotFoundException;
import com.enjoytrip.jwt.JwtUtil;
import com.enjoytrip.jwt.exception.TokenException;
import com.enjoytrip.social.config.OAuthServiceProvider;
import com.enjoytrip.social.domain.SocialType;
import com.enjoytrip.social.service.OAuthService;
import com.enjoytrip.user.service.command.EmailLoginCommand;
import com.enjoytrip.user.service.dto.LoginDTO;
import com.enjoytrip.user.service.dto.UserCommand;
import com.enjoytrip.user.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.enjoytrip.common.exception.ExceptionCode.TOKEN_INVALID;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final UserService userService;
    private final OAuthServiceProvider oAuthServiceProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;


    public LoginDTO socialLogin(String code, SocialType type, String redirectUri) {
        OAuthService oAuthService = oAuthServiceProvider.getService(type);

        if (oAuthService == null) {
            throw new NotFoundException("SOCIAL_NOT_FOUND","지원되지 않는 소셜 로그인 입니다");
        }

        String accessToken = oAuthService.getAccessToken(code, redirectUri);
        UserCommand userCommand = oAuthService.getUserProfile(accessToken);

        UserDTO userDTO = userService.createSocialUser(userCommand);
        String token = jwtUtil.createToken(AuthClaims.fromUser(userDTO), Instant.now());

        return LoginDTO.from( token, userDTO);
    }

    public String getAuthorizationUrl(SocialType type, String redirectUri) {
        OAuthService oAuthService = oAuthServiceProvider.getService(type);

        if (oAuthService == null) {
            throw new NotFoundException("SOCIAL_NOT_FOUND","지원되지 않는 소셜 로그인 입니다");
        }

        return oAuthService.getAuthorizationUrl(redirectUri);
    }

    public LoginDTO emailLogin(EmailLoginCommand command) {
        UserDTO user = userService.getUserByEmail(command.getEmail());

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new TokenException(TOKEN_INVALID);
        }

        String token = jwtUtil.createToken(AuthClaims.fromUser(user), Instant.now());

        return new LoginDTO(token,user.getUserId(), user.getNickname(), user.getAvatarUrl());
    }

}
