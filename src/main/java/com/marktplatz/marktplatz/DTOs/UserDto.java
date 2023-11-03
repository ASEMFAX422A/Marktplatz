package com.marktplatz.marktplatz.DTOs;

import com.marktplatz.marktplatz.entity.User;
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
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String profilePic;
    private List<UserAnzeige> anzeigen;

    public UserDto userDto( User user){
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .profilePic(user.getProfilePic())
                .anzeigen(user.getAnzeigen())
                .build();

    }

    public UserDto OpntionaluserDto(Optional<User> user){
        return userDto(user.get());

    }
    public List<UserDto> AllUsertoDto(List<User> users){
        return users.stream().map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .profilePic(user.getProfilePic())
                        .anzeigen(user.getAnzeigen())
                        .build())
                .collect(Collectors.toList());
    }

}
