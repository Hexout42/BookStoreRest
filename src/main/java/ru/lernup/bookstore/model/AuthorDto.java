package ru.lernup.bookstore.model;

import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class AuthorDto {
    private final Long id;
    private final String allNameAuthor;

}
