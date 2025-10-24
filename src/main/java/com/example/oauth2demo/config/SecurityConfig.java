package com.example.oauth2demo.config;

import com.example.oauth2demo.security.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/error", "/oauth2/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/")
                .successHandler(successHandler)
                .failureUrl("http://localhost:3000/login?error=true")
            )
            .logout(logout -> logout
                .logoutSuccessUrl("http://localhost:3000/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        http.headers(headers -> headers.frameOptions().disable());
        return http.build();
    }
}
