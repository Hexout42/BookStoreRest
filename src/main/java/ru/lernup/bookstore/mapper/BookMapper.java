package ru.lernup.bookstore.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.dao.entity.Book;
import ru.lernup.bookstore.model.BookDto;
import ru.lernup.bookstore.view.BookView;
@Component
public class BookMapper {
    public BookDto mappedToDto(Book book){
        return BookDto.builder()
                .idAuthor(book.getIdAuthor().getAllNameAuthor())
                .ageBook(book.getAgeBook())
                .priceBook(book.getPriceBook())
                .quantity(book.getBookHouse().getQuantity())
                .numberOfPages(book.getNumberOfPages())
                .nameBook(book.getNameBook())
                .id(book.getId())
                .build();
    }
    public BookView mappedToView(BookDto bookDto){
        BookView bookView = new BookView();
        bookView.setAgeBook(bookDto.getAgeBook());
        bookView.setNameBook(bookDto.getNameBook());
        bookView.setPriceBook(bookDto.getPriceBook());
        bookView.setId(bookDto.getId());
        bookView.setIdAuthor(bookDto.getIdAuthor());
        bookView.setQuantity(bookDto.getQuantity());
        bookView.setNumberOfPages(bookDto.getNumberOfPages());
        return bookView;
    }
    public BookDto mappedFromView(BookView bookView){
        return
        BookDto.builder()
                .idAuthor(bookView.getIdAuthor())
                .ageBook(bookView.getAgeBook())
                .priceBook(bookView.getPriceBook())
                .quantity(bookView.getQuantity())
                .nameBook(bookView.getNameBook())
                .numberOfPages(bookView.getNumberOfPages())
                .id(bookView.getId())
                .build();
    }
    public Book mappedFromDto(BookDto bookDto){
        Book book = new Book();
        book.setAgeBook(bookDto.getAgeBook());
        book.getBookHouse().setQuantity(bookDto.getQuantity());
        book.setNameBook(bookDto.getNameBook());
        book.setPriceBook(bookDto.getPriceBook());
        book.setId(book.getId());
        book.getIdAuthor().setAllNameAuthor(bookDto.getIdAuthor());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        return book;
    }
}
