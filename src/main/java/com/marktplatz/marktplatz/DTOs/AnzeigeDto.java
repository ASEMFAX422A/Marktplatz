package com.marktplatz.marktplatz.DTOs;

import com.marktplatz.marktplatz.entity.Anzeige;
import com.marktplatz.marktplatz.entity.UserAnzeige;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnzeigeDto {

    private Long id;
    private String name;
    private String description;
    private String image;
    private double preis;
    private UserAnzeige userAnzeige;

    public AnzeigeDto anzeigeDto (Anzeige anzeige){
        return AnzeigeDto.builder()
                .id(anzeige.getId())
                .name(anzeige.getName())
                .description(anzeige.getDescription())
                .image(anzeige.getImage())
                .preis(anzeige.getPreis())
                .userAnzeige(anzeige.getUserAnzeige())
                .build();
    }

    public AnzeigeDto OpntionalAnzeigeDto(Optional<Anzeige> anzeige){
        return anzeigeDto(anzeige.get());
    }

    public List<AnzeigeDto> AllAnzeigentoDto(List<Anzeige> anzeiges){
        return anzeiges.stream().map(anzeige -> AnzeigeDto.builder()
                        .id(anzeige.getId())
                        .name(anzeige.getName())
                        .description(anzeige.getDescription())
                        .image(anzeige.getImage())
                        .preis(anzeige.getPreis())
                        .userAnzeige(anzeige.getUserAnzeige())
                        .build())
                .collect(Collectors.toList());
    }
}
