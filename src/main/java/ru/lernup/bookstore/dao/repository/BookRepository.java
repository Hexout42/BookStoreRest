package ru.lernup.bookstore.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.bookstore.dao.entity.Book;



import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByNameBookContainsAndPriceBook(String nameBook, int priceBook);
    Book findBooksById(Long id);
}