package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetailsOrderView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Long idBook;
    @JsonProperty
    private Integer quantity;

}
