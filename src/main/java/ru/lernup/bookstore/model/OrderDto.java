package ru.lernup.bookstore.model;



import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class OrderDto {
    private final Long id;
    private final Integer cost;
    private final String date;

}
