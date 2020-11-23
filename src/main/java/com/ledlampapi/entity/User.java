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
    @Column(name = "name",unique = true, nullable = false, length = 100)
    private String email;

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    private String name;

    @Basic
    @Column(name = "password", nullable = false, length = 200)
    private String password;
}
