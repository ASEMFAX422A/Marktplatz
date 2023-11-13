package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.Roles.Role;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.repository.UserReop;
import com.marktplatz.marktplatz.security.JwtGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UserService implements UserDetailsService {
    @Autowired
    private UserReop userReop;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;
    private final JwtGenerator jwtGenerator;


    public List<User> getAllUser(){
        return userReop.findAll();
    }
    public ResponseEntity<UserDto> getUserById(Long id){
        return  ResponseEntity.ok(new UserDto().OpntionaluserDto(userReop.findById(id)));
    }
    public ResponseEntity<UserDto> getByUsername(String username){
        if (username.isEmpty()) {
            return (ResponseEntity<UserDto>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(new UserDto().userDto(userReop.findeByUsername(username)));
    }

    //TODO: muss Noch angepasst werden. soll geprüft werden ob der User bzw. Username schon existiert.✅
    public ResponseEntity<UserDto> addUser(UserDto user){
        if (new UserDto().userDto(userReop.findeByUsername(user.getUsername()))!=null) {throw new IllegalStateException("username already taken");}
        if (user.getRole()== null){user.setRole(Role.USER);}
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(new UserDto().userDto(userReop.save(new User().toUser(user))));
    }

    public void updateUserById(UserDto user) {
        userReop.updateUserById(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getProfilePic(),
                user.getRole());}
    public void deleteUser(Long id){userReop.deleteById(id);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userReop.findeByUsername(username);
    }

    public ResponseEntity<String> login(UserDto userDto){
        if (userDto == null) {
            return ResponseEntity.status(400).body("Unbekanter user");
        }
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return ResponseEntity.ok("Bearer " + jwtGenerator.generateToken(auth));
    }
    }

