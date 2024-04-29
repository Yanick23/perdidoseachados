package com.perdidoseachados.perdidoseachados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/usuarios/login", // Url que usaremos para fazer login
            "/usuarios/registar","/itens/meusitens"  // Url que usaremos para criar um usuário

    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {

            "/categorias/**","/categorias","/localizacoes","/localizacoes/**"

    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_USER_ADMIN = {
            "/itens/registar" // URL que requer autenticação para acessar os itens do usuário
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(cs -> cs.disable()).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(
                        request -> request
                                .requestMatchers(HttpMethod.POST,ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                                .requestMatchers(HttpMethod.POST,ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                                .requestMatchers(HttpMethod.POST, ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_USER_ADMIN).authenticated() // Precisa de autenticação tanto para usuários quanto para administradores
                                .requestMatchers(HttpMethod.GET, ENDPOINTS_WITH_AUTHENTICATION_REQUIRED_USER_ADMIN).authenticated() // Precisa de autenticação tanto para usuários quanto para administradores


                                .anyRequest().authenticated()

                                )
                .addFilterBefore(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();
    }



    @Bean
    public UserAuthenticationFilter userAuthenticationFilter() {
        return new UserAuthenticationFilter();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
