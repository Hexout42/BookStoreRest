package ru.lernup.bookstore.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "id_consumer")
    private Consumer consumer;

    @Column(name = "cost")
    private int cost;
    @Column(name = "date")
    private String date;
    @OneToMany(mappedBy = "idOrder", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DetailsOrder> detailsOrders;


    @Override
    public String toString() {
        return "order :" + id   ;

    }
}
