package com.cs.user.system.user.service.domain.config;

import com.cs.user.system.user.service.domain.UserDomainServiceImpl;
import com.cs.user.system.user.service.domain.service.UserDomainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Value("${user-service.minimum-registration-age}")
    private int minimumRegistrationAge;

    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainServiceImpl(minimumRegistrationAge);
    }
}
