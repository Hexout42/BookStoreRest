package ru.lernup.bookstore.model;

import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class BookDto {
    private final Long id;
    private final String nameBook;
    private final String idAuthor;
    private final Integer ageBook;
    private final Integer numberOfPages;
    private final Integer priceBook;
    private final Integer quantity;


}
