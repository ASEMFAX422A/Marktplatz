package com.marktplatz.marktplatz.entity;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.Roles.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//TODO: Noch ein Role Colum einfügen.✅
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"User\"")
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    //TODO: heir muss angepasst werden,sodass die Rolen in der Datenbank richtig gespeichert werden

    @Enumerated(EnumType.STRING)
    @Column(name ="role" ,columnDefinition = "VARCHAR(255) DEFAULT 'USER'")
    private Role role;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;


    @Lob
    @Column
    private byte[] profilePic;

    @OneToMany(mappedBy = "user")
    private List<UserAnzeige> anzeigen;

    public User toUser(UserDto userDto){
        return User.builder()
                    .id(userDto.getId())
                    .name(userDto.getName())
                    .username(userDto.getUsername())
                    .role(userDto.getRole())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .profilePic(userDto.getProfilePic())
                    .anzeigen(userDto.getAnzeigen())
                    .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(auth);
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
