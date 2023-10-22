package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.marktplatz.marktplatz.repository.UserReop;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService<T> {
    @Autowired
    UserReop userReop;


    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userReop.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> getUser(Long id){
        Optional<User> userOptional = userReop.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<T> addUser(User user){
        if (user.getName()==null) {
            return null;
        }
        return ResponseEntity.ok((T) userReop.save(user));
    }

    public void updateUserById(User user){
       userReop.updateUserById(user.getId(), user.getName(), user.getEmail(),user.getPassword());}
    public void deleteUser(User user){userReop.delete(user);}
}
