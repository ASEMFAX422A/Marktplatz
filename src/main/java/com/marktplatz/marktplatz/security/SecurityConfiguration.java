package com.marktplatz.marktplatz.security;


public class SecurityConfiguration  {


/*

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

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


 */

}
