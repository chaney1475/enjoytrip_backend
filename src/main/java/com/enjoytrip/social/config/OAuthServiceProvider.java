package com.enjoytrip.social.config;

import com.enjoytrip.common.exception.BadRequestException;
import com.enjoytrip.social.domain.SocialType;
import com.enjoytrip.social.service.OAuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class OAuthServiceProvider {
    private final ApplicationContext applicationContext;
    private final Map<SocialType, OAuthService> serviceMap = new EnumMap<>(SocialType.class);

    public OAuthServiceProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        for (SocialType type : SocialType.values()) {
            if (type.getClazz() != null) {
                serviceMap.put(type, applicationContext.getBean(type.getClazz()));
            }
        }
    }

    public OAuthService getService(SocialType type) {
        OAuthService service = serviceMap.get(type);
        if (service != null) {
            return service;
        }
        throw new BadRequestException("", "지원하지 않는 소셜 로그인입니다. " + type.name());
    }
}
