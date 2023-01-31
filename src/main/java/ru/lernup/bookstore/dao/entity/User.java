package ru.lernup.bookstore.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "user_auth")
public class User {
    @Id
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Role> roles;
    @OneToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumerAuth;
}
