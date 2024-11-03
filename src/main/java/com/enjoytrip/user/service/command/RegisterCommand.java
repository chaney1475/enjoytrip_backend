package com.enjoytrip.user.service.command;

import com.enjoytrip.user.controller.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCommand {
    private final String nickname;
    private final String email;
    private final String password;

    static public RegisterCommand from(RegisterRequest request) {
        return new RegisterCommand(
                request.getNickname(),
                request.getEmail(),
                request.getPassword()
        );
    }
}
