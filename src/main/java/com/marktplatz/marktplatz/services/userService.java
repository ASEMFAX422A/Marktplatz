package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.marktplatz.marktplatz.repository.UserReop;
import java.util.List;
import java.util.Optional;

@Service
public class userService {
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

    public ResponseEntity<User> addUser(User user){
        return new ResponseEntity<>(userReop.save(user),HttpStatus.OK);
    }

    public  Optional<User> updateUserById(User user, Long id){
        return userReop.updateUserById(id, user.getName(), user.getEmail(),user.getPassword());
    }
}
