package com.marktplatz.marktplatz.DTOs;

import com.marktplatz.marktplatz.entity.Anzeige;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.entity.UserAnzeige;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAnzeigeDto {
    private long id;

    private User user;

    private Anzeige anzeige;

    public List<UserAnzeigeDto> AllUserAnzeigentoDto(List<UserAnzeige> anzeiges){
        return anzeiges.stream().map(anzeige -> UserAnzeigeDto.builder()
                        .id(anzeige.getId())
                        .user(anzeige.getUser())
                        .anzeige(anzeige.getAnzeige())
                        .build())
                .collect(Collectors.toList());
    }
}
