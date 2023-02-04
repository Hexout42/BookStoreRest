package ru.lernup.bookstore.security.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class SecurityConfig  {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .authorizeHttpRequests((authz) -> authz
                            .requestMatchers("/user/**").hasRole("USER")
                            .requestMatchers("/user/register").anonymous()
                            .requestMatchers("/orders/**").hasRole("ADMIN")
                            .requestMatchers("/book/**").permitAll()
                            .requestMatchers("/author").authenticated()
                            .anyRequest().authenticated())
                    .formLogin();



            return http.build();

        }



    @Bean
    public BCryptPasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
    }



}
