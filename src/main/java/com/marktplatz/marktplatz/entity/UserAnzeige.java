package com.marktplatz.marktplatz.entity;

import jakarta.persistence.*;

@Entity
@Table
public class UserAnzeige {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id",insertable=false, updatable=false)
    private User user;

    @OneToOne
    @JoinColumn(name = "id",insertable=false, updatable=false)
    private Anzeige anzeige;


    public UserAnzeige() {
    }
}
