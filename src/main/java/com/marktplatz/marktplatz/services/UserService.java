package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService<T> {
    @Autowired
    private UserReop userReop;


    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(new UserDto().AllUsertoDto(userReop.findAll()));
    }
    public ResponseEntity<T> getUserById(Long id){
        return (ResponseEntity<T>) ResponseEntity.ok(new UserDto().OpntionaluserDto(userReop.findById(id)));
    }
    public ResponseEntity<T> getByUsername(String username){
        if (username.isEmpty()) {
            return (ResponseEntity<T>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok((T)new UserDto().userDto(userReop.findeByUsername(username)));
    }

    //TODO: muss Noch angepasst werden. soll geprüft werden ob der User bzw. Username schon existiert.✅
    public ResponseEntity<T> addUser(UserDto user){
        if (user.getUsername().isEmpty()&&new UserDto().userDto(userReop.findeByUsername(user.getUsername()))!=null) {return (ResponseEntity<T>) ResponseEntity.badRequest();}
        return (ResponseEntity<T>) ResponseEntity.ok(new UserDto().userDto(userReop.save(new User().toUser(user))));
    }

    public void updateUserById(UserDto user) {
        userReop.updateUserById(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getProfilePic());}
    public void deleteUser(Long id){userReop.deleteById(id);}
}
