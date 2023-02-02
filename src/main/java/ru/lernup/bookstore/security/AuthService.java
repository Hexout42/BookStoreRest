package ru.lernup.bookstore.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lernup.bookstore.service.UserService;
@Service
@Slf4j
public class AuthService implements UserDetailsService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserJwt loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJwt user = UserJwtFactory.createUser(userService.getUserByLogin(username));

        if(user == null){
            throw  new UsernameNotFoundException("Пользователь не найден");
        }
        log.info("find user : {}",user.getUsername());
        return user;
    }
}
