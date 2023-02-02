package ru.lernup.bookstore.security;

import ru.lernup.bookstore.dao.entity.User;
import ru.lernup.bookstore.model.UserRole;

import java.util.stream.Collectors;

public class UserJwtFactory {
    public static UserJwt createUser(User user){
        return UserJwt.builder()
                .login(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(role -> {
                    return new UserRole(role.getRole());
                }).collect(Collectors.toList()))
                .build();
    }
}
