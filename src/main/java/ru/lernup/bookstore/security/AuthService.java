package ru.lernup.bookstore.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lernup.bookstore.model.UserDto;
import ru.lernup.bookstore.service.UserService;
@Service
public class AuthService implements UserDetailsService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.getUserByLogin(username);
        if(user == null){
            throw  new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }
}
