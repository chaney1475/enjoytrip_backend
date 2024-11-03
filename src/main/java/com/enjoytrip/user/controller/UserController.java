package com.enjoytrip.user.controller;

import com.enjoytrip.user.controller.request.RegisterRequest;
import com.enjoytrip.user.service.UserService;
import com.enjoytrip.user.service.command.RegisterCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/user", produces = "application/json")
public class UserController {
    private final UserService userService;

    // 회원가입 엔드포인트
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        userService.createEmailUser(RegisterCommand.from(request));
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }

    // 로그아웃 엔드포인트
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
//        userService.logout(token);
//        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
//    }
}
