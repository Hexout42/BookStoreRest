package ru.lernup.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.bookstore.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);
}
