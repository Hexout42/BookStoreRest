package ru.lernup.bookstore.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Role;
import ru.lernup.bookstore.dao.entity.User;
import ru.lernup.bookstore.model.UserDto;
import ru.lernup.bookstore.model.UserRole;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserMapper {
    public UserDto mappedToDto(User user) {
        return UserDto.builder()
                .login(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(role -> {
                    return new UserRole(role.getRole());
                }).collect(Collectors.toList()))
                .build();
    }

    public User mappedFromDto(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getLogin());
        user.setPassword(userDto.getPassword());


        user.setRoles(userDto.getRoles().stream().map(roles ->{
            Role role = new Role();
            role.setUser(user);
            role.setRole(roles.getRole());
            return  role;
        }).collect(Collectors.toList()));
        return  user;
    }
}