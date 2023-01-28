package ru.lernup.bookstore.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailsOrderDto {
    private final Long idBook;
    private final Integer quantity;
   private final Long id;



}
