package com.enjoytrip.user.service.command;

import com.enjoytrip.user.controller.request.EmailLoginRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailLoginCommand {
    private String email;
    private String password;

    public static EmailLoginCommand from(EmailLoginRequest request) {
        return new EmailLoginCommand(request.getEmail(), request.getPassword());
    }
}
