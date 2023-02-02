package ru.lernup.bookstore.security.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ru.lernup.bookstore.security.AuthService;

import ru.lernup.bookstore.security.JwtTokenProvider;

import ru.lernup.bookstore.security.filters.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final JwtTokenProvider provider;
    private final AuthService authService;


    public SecurityConfig(JwtTokenProvider provider, AuthService authService) {

        this.provider = provider;
        this.authService = authService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(provider);
      return   http.httpBasic().disable()
                        .csrf().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/refreshAccessToken").authenticated()
                .requestMatchers("/orders/**").hasRole("ADMIN")
                .requestMatchers("/book/**").permitAll()
                .requestMatchers("/author").authenticated()
                .anyRequest().authenticated()
                        .and()
                .addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class))
                .build();
    }



    @Bean
    public BCryptPasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
    }



}
