package com.bazzi.cherryfeed.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 스프링 시큐리티(Spring Security) 프레임워크에서 제공하는 클래스 중 하나로
// 비밀번호를 암호화 하는데 사용할 수 있는 메서드를 가진 클래스
@Configuration
public class EncoderConfig {
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
