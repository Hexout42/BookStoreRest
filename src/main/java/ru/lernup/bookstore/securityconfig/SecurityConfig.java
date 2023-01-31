package ru.lernup.bookstore.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authz) -> authz
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
