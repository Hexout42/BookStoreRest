package ru.lernup.bookstore.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.lernup.bookstore.dao.entity.User;
import ru.lernup.bookstore.dao.repository.UserRepository;

@Repository

public class UserService {
private final BCryptPasswordEncoder encoder;

    private final UserRepository userRepository;

    public UserService(
            BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;

        this.userRepository = userRepository;
    }

    public User getUserByLogin(String login){
        try {
            User user = userRepository.findUserByUserName(login);
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            return user;
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
