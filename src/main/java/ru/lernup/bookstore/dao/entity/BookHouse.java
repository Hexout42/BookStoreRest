package ru.lernup.bookstore.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "book_house")

public class BookHouse {
    @Id
    @OneToOne
    @JoinColumn(name = "id_book")
    private Book book;


    @Column(name = "quantity")
    private int quantity;
    @Version
    @Column(name = "version")
    private Long version;

}
