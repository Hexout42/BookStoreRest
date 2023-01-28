package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class OrderView {
    @JsonProperty
    private Long id;

    @JsonProperty
    private Integer cost;
   @JsonProperty
    private String date;


}
