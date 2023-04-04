package com.bazzi.cherryfeed.configuration;

import com.bazzi.cherryfeed.apps.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;
    @Value("${jwt.token.secret}") //application.yml에
    private String key;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() //UI로 안하고 토큰인증으로 할것이기 때문에 disable한다.
                .csrf().disable()      //크로스사이트기능
                .cors().and()          //크로스사이트에서 도메인이다를때 허용한다.
                .authorizeRequests()
                .antMatchers("/api/v1/users/join", "/api/v1/users/login", "/api/v1/users/duplicationcheck/nickname", "/api/v1/users/create/connectcode", "/swagger-ui.html/**").permitAll() //join,loin은 언제나 허용한다.
                .antMatchers(HttpMethod.POST, "/api/**").authenticated() //비허용
                .antMatchers(HttpMethod.GET, "/api/**").authenticated() //비허용
                .antMatchers(HttpMethod.PUT, "/api/**").authenticated() //비허용
                .antMatchers(HttpMethod.DELETE, "/api/**").authenticated() //비허용
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt를 사용하는경우 씀
                .and()
                .addFilterBefore(new JwtFilter(userService, key), UsernamePasswordAuthenticationFilter.class) //이부분 중요 필터로 걸려서 토큰을 정보로 풀어주는과정
                .build();
    }

}


