package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String nameBook;
    @JsonProperty
    private String idAuthor;
    @JsonProperty
    private Integer ageBook;
    @JsonProperty
    private Integer numberOfPages;
    @JsonProperty
    private Integer priceBook;
    @JsonProperty
    private Integer quantity;


}
