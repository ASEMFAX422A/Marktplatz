package com.marktplatz.marktplatz.security;


import com.marktplatz.marktplatz.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    public SecurityConfiguration(@Lazy UserService userService, PasswordEncoder passwordEncoder, JwtAuthFilter jwtAuthFilter) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/v1/auth/**")
                        .ignoringRequestMatchers("/api/v1/anzeige/**")
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"api/v1/anzeige/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"api/v1/anzeige/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/user/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {

        return authConfig.getAuthenticationManager();
    }

}
