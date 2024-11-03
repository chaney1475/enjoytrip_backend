package com.enjoytrip.user.controller;


import com.enjoytrip.social.domain.SocialType;
import com.enjoytrip.user.controller.request.EmailLoginRequest;
import com.enjoytrip.user.controller.request.SocialLoginRequest;
import com.enjoytrip.user.controller.response.LoginResponse;
import com.enjoytrip.user.controller.response.UriResponse;
import com.enjoytrip.user.service.LoginService;
import com.enjoytrip.user.service.command.EmailLoginCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/login", produces = "application/json")
public class LoginController {
    private final LoginService loginService;

    @Operation(
            summary = "OAuth URL 생성",
            description = "OAuth 인증을 위한 URL을 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OAuth 인증 URL을 반환합니다.")
            }
    )
    @GetMapping("/oauth/{provider}/authorize")
    public ResponseEntity<UriResponse> getAuthorizationUrl(
            @PathVariable SocialType provider,
            @RequestParam String redirectUri
    ) {
        log.info("getAuthorizationUrl provider={}, redirectUri={}", provider, redirectUri);
        String authorizationUrl = loginService.getAuthorizationUrl(provider, redirectUri);
        log.info("authorizationUrl={}", authorizationUrl);
        return ResponseEntity.ok(new UriResponse(authorizationUrl));
    }

    @Operation(
            summary = "소셜 로그인",
            description = "소셜 프로바이더를 통해 로그인하고 JWT 토큰을 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "JWT 토큰을 반환합니다.")
            }
    )
    @PostMapping("/social/{provider}")
    public ResponseEntity<LoginResponse> socialLogin(
            @PathVariable SocialType provider,
            @RequestBody SocialLoginRequest body
    ) {
        LoginResponse response = LoginResponse.from(
                loginService.socialLogin(body.getCode(), provider, body.getRedirectUri())
        );
        System.out.println(response.getToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/email")
    public ResponseEntity<LoginResponse> emailLogin(@RequestBody EmailLoginRequest request) {
        log.info("Email login attempt for email={}", request.getEmail());

        LoginResponse response = LoginResponse.from(loginService.emailLogin(
                EmailLoginCommand.from(request)
        ));

        return ResponseEntity.ok(response);
    }
}
