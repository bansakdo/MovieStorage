package com.moviestorage.moviestorage.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    UserDetailsService userDetailsService;
    ObjectMapper objectMapper;

    @Autowired
    public SecurityConfig(
            UserDetailsService userDetailsService,
            ObjectMapper objectMapper
    ) {
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));  // React dev server
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(final @NonNull HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/users/**").hasAnyRole("ADMIN", "MANAGER")
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(login ->
                        login
                                .loginProcessingUrl("/api/auth/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .successHandler((request, response, authentication) -> {
                                    log.debug("login success! " + authentication.getName());
                                    response.setStatus(HttpStatus.OK.value());
                                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                    Map<String, Object> body = new HashMap<>();
                                    body.put("success", true);
                                    body.put("username", authentication.getName());
                                    objectMapper.writeValue(response.getWriter(), body);
                                })
                                .failureHandler((request, response, exception) -> {
                                    log.debug("login failed: " + exception.getMessage());
                                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                    Map<String, Object> body = new HashMap<>();
                                    body.put("success", false);
                                    body.put("message", "로그인에 실패했습니다.");
                                    objectMapper.writeValue(response.getWriter(), body);
                                })
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/api/auth/logout")
                                .addLogoutHandler((request, response, authentication) -> {
                                    HttpSession session = request.getSession(false);
                                    if (session != null) session.invalidate();
                                })
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    response.setStatus(HttpStatus.OK.value());
                                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                    Map<String, Object> body = new HashMap<>();
                                    body.put("success", true);
                                    objectMapper.writeValue(response.getWriter(), body);
                                })
                                .deleteCookies("remember-me", "JSESSIONID")
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            Map<String, Object> body = new HashMap<>();
                            body.put("success", false);
                            body.put("message", "인증이 필요합니다.");
                            objectMapper.writeValue(response.getWriter(), body);
                        })
                )
                .rememberMe(rememberMe ->
                        rememberMe
                                .rememberMeParameter("remember")
                                .tokenValiditySeconds(3600)
                                .userDetailsService(userDetailsService)
                )
                .build();
    }
}
