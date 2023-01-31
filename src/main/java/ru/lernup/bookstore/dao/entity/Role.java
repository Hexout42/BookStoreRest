package ru.lernup.bookstore.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "role")
    private String role;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
