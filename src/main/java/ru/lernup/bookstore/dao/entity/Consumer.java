package ru.lernup.bookstore.dao.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "consumer")


public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "all_name_consumer")
    private String allNameConsumer;

    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "mail")
    private String mail;
    @OneToMany(mappedBy = "consumer", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Order> order;
  @OneToOne(mappedBy = "consumerAuth", fetch = FetchType.EAGER,cascade = CascadeType.ALL)

    private User user;
    @Override
    public String toString() {
        return allNameConsumer + " " + birthDate ;
    }



}

