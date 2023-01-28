package ru.lernup.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.bookstore.dao.entity.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByAllNameAuthor(String name);
}