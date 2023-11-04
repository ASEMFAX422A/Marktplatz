package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.Roles.Role;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserReop userReop;


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
        if (new UserDto().userDto(userReop.findeByUsername(user.getUsername()))!=null) {return (ResponseEntity<UserDto>) ResponseEntity.badRequest();}
        if (user.getRole()== null){user.setRole(Role.USER);}
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
}
