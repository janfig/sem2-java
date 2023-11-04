package com.example.lab6.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    private String name;

    private String surname;

    private String login;

    private String password;

    public User() {
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
