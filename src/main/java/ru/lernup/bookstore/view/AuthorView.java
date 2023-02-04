package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class AuthorView {
    @JsonProperty(required = false)
    private Long id;
    @JsonProperty
    private String allNameAuthor;

}
