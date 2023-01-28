package ru.lernup.bookstore.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "book")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "name_book")
    private String nameBook;

    @JoinColumn(name = "id_author")
    @ManyToOne
    private Author idAuthor;
    @Column(name = "age_book")
    private int ageBook;
    @Column(name="number_of_pages")
    private int numberOfPages;
    @Column(name="price_book")
    private int priceBook;
    @OneToOne(mappedBy = "book")
    private BookHouse bookHouse;
    @OneToMany(mappedBy = "idBook")
    private List<DetailsOrder> detailsOrder;

    @Override
    public String toString() {
        return  nameBook + " " + idAuthor.getAllNameAuthor() + " Год выпуска " + ageBook + " колличество страниц "
        + numberOfPages + " стоимость: " + priceBook  + "колличество " + bookHouse.getQuantity();
    }
}
