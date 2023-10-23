package com.marktplatz.marktplatz.services;

import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService<T> {
    @Autowired
    UserReop userReop;


    public ResponseEntity<List<T>> getAllUser(){
        return new ResponseEntity<>(userReop.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<T> getUserById(Long id){
        Optional<User> userOptional = userReop.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok((T) user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<T> getByUsername(String username){
        if (username == null) {
            return null;
        }
        return ResponseEntity.ok((T) userReop.findeByUsername(username));
    }



    public ResponseEntity<T> addUser(User user){
        if (user.getName()==null) {
            return null;
        }
        return ResponseEntity.ok((T) userReop.save(user));
    }

    public void updateUserById(User user){
       userReop.updateUserById(user.getId(), user.getName(), user.getEmail(),user.getPassword());}
    public void deleteUser(Long id){userReop.deleteById(id);}
}
