package com.marktplatz.marktplatz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "\"User\"")
    public class User {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;

        @Column
        private String name;

        @Column
        private String email;

        @Column
        private String username;

        @Column
        private String password;

        @OneToMany(mappedBy = "user")
        private List<UserAnzeige> anzeigen;

    }
