package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.Roles.Role;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.repository.UserReop;
import com.marktplatz.marktplatz.security.JwtGenerator;
import com.marktplatz.marktplatz.security.PasswordEncoder;
import com.marktplatz.marktplatz.security.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService implements UserDetailsService {
    @Autowired
    private UserReop userReop;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authManager;
    @Autowired
    private final JwtGenerator jwtGenerator;


    public List<User> getAllUser(){
        return userReop.findAll();
    }
    public ResponseEntity<UserDto> getUserById(Long id){
        return  ResponseEntity.ok(new UserDto().OpntionaluserDto(userReop.findById(id)));
    }
    public ResponseEntity<UserDto> getByUsername(UserDto userDto){
        if (userDto.getUsername().isEmpty()) {
            return (ResponseEntity<UserDto>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(new UserDto().userDto(userReop.findeByUsername(userDto.getUsername())));
    }

    //TODO: muss Noch angepasst werden. soll geprüft werden ob der User bzw. Username schon existiert.✅
    public ResponseEntity<UserDto> addUser(UserDto user){
        if (new UserDto().userDto(userReop.findeByUsername(user.getUsername()))!=null) {throw new IllegalStateException("username already taken");}
        if (user.getRole()== null){user.setRole(Role.USER);}
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
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

    public Boolean login(User request){
        if (request == null) {
            throw new IllegalStateException("Eingabe ist Null");
        }
        try {
            UserDto vPassword=new UserDto().userDto(request);
            UserDto uPasswoed=new UserDto().userDto(userReop.findeByUsername(request.getUsername()));
            return passwordEncoder.bCryptPasswordEncoder().matches(vPassword.getPassword(),uPasswoed.getPassword());
        }catch (Exception e){ throw new IllegalStateException("Username oder Password ist Falsch!");}

    }

    }

