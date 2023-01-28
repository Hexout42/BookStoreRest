package ru.lernup.bookstore.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "details_order")

public class DetailsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @JoinColumn(name = "id_order")
     @ManyToOne
    private Order idOrder;
   @ManyToOne
   @JoinColumn(name = "id_book")

    private Book idBook;
    @Column(name = "quantity")
    private int quantity;


    @Override
    public String toString() {
        return  idBook.getId() + "\t" + quantity + "\t" + id;

    }
}
