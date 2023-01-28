package ru.lernup.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.bookstore.dao.entity.Consumer;


public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}