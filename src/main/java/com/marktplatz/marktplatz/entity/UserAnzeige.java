package com.marktplatz.marktplatz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnzeige {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "anzeige_id", insertable = false, updatable = false)
    private Anzeige anzeige;


    public UserAnzeige(User user, Anzeige anzeige) {
        this.anzeige=anzeige;
        this.user=user;
    }
}
