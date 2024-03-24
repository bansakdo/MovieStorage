package com.moviestorage.moviestorage.security;


import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(
            UserDetailsService userDetailsService
    ) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(final @NotNull HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/user/**")        .authenticated()
                                .requestMatchers("/security/**", "/userList")    .hasAnyRole("ADMIN", "MANAGER")
                                .requestMatchers("/manager/**")     .hasAnyRole("ADMIN", "MANAGER")
                                .requestMatchers("/admin/**")       .hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin(login ->
                        login
                                .loginPage("/login")                    // 로그인화면 URL
                                .defaultSuccessUrl("/")                 // 로그인 성공 시 URL
                                .failureUrl("/login?error=true")        // 로그인 실패 시 URL
                                .loginProcessingUrl("/login")           // 로그인화면 form 태그 내 action 값
                                .usernameParameter("username")          // 로그인화면 from 태그 내 ID 값
                                .passwordParameter("password")          // 로그인화면 form 태그 내 PW 값
                                .successHandler((request, response, authentication) -> {        // 로그인 성공 시 handler
//                                    System.out.println("login success!");
                                    log.debug("login success!" + authentication.getName());
                                    response.sendRedirect("/");
                                })
                                .failureHandler((request, response, exception) -> {             // 로그인 실패 시 handler
                                    log.debug("login failed: " + exception.getMessage());
                                    response.sendRedirect("/login?exception=true");
                                })
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .addLogoutHandler((request, response, authentication) -> {
                                    HttpSession session = request.getSession();
                                    session.invalidate();
                                })
                                .logoutSuccessHandler((request, response, authentication) -> {
                                    response.sendRedirect("/login");
                                })
                                .deleteCookies("remember-me")
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
