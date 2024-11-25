package com.enjoytrip.user.controller;

import com.enjoytrip.auth.annotation.Authenticated;
import com.enjoytrip.auth.annotation.LoginRequired;
import com.enjoytrip.auth.domain.AuthClaims;
import com.enjoytrip.user.controller.request.RegisterRequest;
import com.enjoytrip.user.controller.request.UserUpdateRequest;
import com.enjoytrip.user.controller.response.UserResponse;
import com.enjoytrip.user.service.UserService;
import com.enjoytrip.user.service.command.RegisterCommand;
import com.enjoytrip.user.service.command.UserUpdateCommand;
import com.enjoytrip.user.service.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @LoginRequired
    @PutMapping("/me")
    @Operation(summary = "현재 사용자가 자신의 정보 수정", description = "AuthClaims 의 userId로 유저를 수정한다.")
    public ResponseEntity<UserResponse> updateMe(
            @Parameter(hidden = true) @Authenticated AuthClaims authClaims,
            @Valid @RequestBody UserUpdateRequest body) {
        UserDTO updatedUser = userService.updateUser(UserUpdateCommand.from(body, authClaims.getUserId()));
        return ResponseEntity.ok(UserResponse.fromDto(updatedUser));
    }

    @LoginRequired
    @GetMapping("/me")
    @Operation(summary = "현재 사용자가 자신의 정보 조회", description = "AuthClaims 의 userId로 유저를 조회한다.")
    public ResponseEntity<UserResponse> getMe(
            @Parameter(hidden = true) @Authenticated AuthClaims authClaims) {
        UserDTO user = userService.getUserById(authClaims.getUserId());
        return ResponseEntity.ok(UserResponse.fromDto(user));
    }

    @LoginRequired
    @DeleteMapping("/me")
    @Operation(summary = "회원 탈퇴", description = "현재 사용자의 계정을 완전히 삭제합니다.")
    public ResponseEntity<String> deleteMe(@Parameter(hidden = true) @Authenticated AuthClaims authClaims) {
        userService.deleteUser(authClaims.getUserId());
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }

    // 로그아웃 엔드포인트
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
//        userService.logout(token);
//        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
//    }
}
