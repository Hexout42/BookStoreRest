package ru.lernup.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.bookstore.dao.entity.Consumer;
import ru.lernup.bookstore.dao.entity.User;


public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    Consumer findConsumerByUser(User user);

}