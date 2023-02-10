package ru.lernup.bookstore.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {
    @JsonProperty
    private Long id;

    @JsonProperty
    private Integer cost;
   @JsonProperty
    private String date;


}
