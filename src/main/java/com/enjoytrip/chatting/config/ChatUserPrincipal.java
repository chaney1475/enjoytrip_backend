package com.enjoytrip.chatting.config;
import com.enjoytrip.auth.domain.AuthClaims;
import lombok.AllArgsConstructor;

import java.security.Principal;


@AllArgsConstructor
public class ChatUserPrincipal implements Principal {
    private final String userId;

    @Override
    public String getName() {
        return userId;
    }

    public static ChatUserPrincipal from(AuthClaims authClaims) {
        return new ChatUserPrincipal(String.valueOf(authClaims.getUserId()));
    }

}
