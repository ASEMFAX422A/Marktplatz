package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class userController <T> {

    @Autowired
    com.marktplatz.marktplatz.services.userService userService;


    @GetMapping("/alluser")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser().getBody(), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<T> getUserById(@PathVariable Long id){
        return new ResponseEntity<T>((T) userService.getUser(id),HttpStatus.OK);
    }
}
