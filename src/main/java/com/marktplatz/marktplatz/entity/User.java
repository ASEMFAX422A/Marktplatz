package com.marktplatz.marktplatz.entity;

import com.marktplatz.marktplatz.DTOs.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"User\"")
@Builder
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

    @Column
    private String profilePic;

    @OneToMany(mappedBy = "user")
    private List<UserAnzeige> anzeigen;

    public User toUser(UserDto userDto){
        return User.builder()
                    .id(userDto.getId())
                    .name(userDto.getName())
                    .username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .profilePic(userDto.getProfilePic())
                    .anzeigen(userDto.getAnzeigen())
                    .build();
    }
}
