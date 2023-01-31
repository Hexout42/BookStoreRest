package ru.lernup.bookstore.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.lernup.bookstore.dao.entity.User;
import ru.lernup.bookstore.dao.repository.UserRepository;
import ru.lernup.bookstore.mapper.UserMapper;
import ru.lernup.bookstore.model.UserDto;
@Repository

public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder encoder,
                       UserMapper userMapper,
                       UserRepository userRepository) {
        this.encoder = encoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto getUserByLogin(String login){
        try {
            User user = userRepository.findUserByUserName(login);
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            return userMapper.mappedToDto(user);
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
