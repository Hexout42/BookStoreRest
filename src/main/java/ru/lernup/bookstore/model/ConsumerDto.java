package ru.lernup.bookstore.model;



import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ConsumerDto {
    private final Long id;
    private final String allNameConsumer;
    private final String birthDate;
    private final String mail;

}
