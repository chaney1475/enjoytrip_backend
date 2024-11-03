package com.enjoytrip.chatting.interceptor;

import com.enjoytrip.auth.domain.AuthClaims;
import com.enjoytrip.chatting.config.ChatUserPrincipal;
import com.enjoytrip.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = jwtService.extractTokenFromHeader(accessor.getFirstNativeHeader("Authorization"));

            if (token != null) {
                AuthClaims claims = jwtService.getClaims(token);
                accessor.setUser(ChatUserPrincipal.from(claims));
            }
        }
        return message;
    }

}
