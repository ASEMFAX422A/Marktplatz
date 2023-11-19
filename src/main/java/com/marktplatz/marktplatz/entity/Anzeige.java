package com.marktplatz.marktplatz.entity;

import com.marktplatz.marktplatz.DTOs.AnzeigeDto;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
@Builder
public class Anzeige {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Lob
    @Column
    private byte[] image;
    @Column
    private double preis;

    @OneToOne(mappedBy = "anzeige")
    private UserAnzeige userAnzeige;

    public Anzeige toAnzeige(AnzeigeDto anzeigeDto){
        return Anzeige.builder()
                .id(anzeigeDto.getId())
                .name(anzeigeDto.getName())
                .description(anzeigeDto.getDescription())
                .image(anzeigeDto.getImage())
                .preis(anzeigeDto.getPreis())
                .userAnzeige(anzeigeDto.getUserAnzeige())
                .build();
    }

}
