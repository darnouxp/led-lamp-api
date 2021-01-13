package com.ledlampapi.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user", schema = "ledlampapi", catalog = "")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "email",unique = true, nullable = false, length = 100)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Basic
    @Column(name = "red", nullable = true, length = 11)
    private Integer red;

    @Basic
    @Column(name = "green", nullable = true, length = 11)
    private Integer green;

    @Basic
    @Column(name = "blue", nullable = true, length = 11)
    private Integer blue;

    @Basic
    @Column(name = "firstname", nullable = true, length = 100)
    private String firstname;

    @Basic
    @Column(name = "lastname", nullable = true, length = 100)
    private String lastname;

    @Basic
    @Column(name = "age", nullable = false, length = 11)
    private Integer age;
}
