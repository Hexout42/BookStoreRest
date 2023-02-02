package ru.lernup.bookstore.security.exeption;


import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {


    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
