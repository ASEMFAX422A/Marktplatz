package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@Controller
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class userController <T> {
    @Autowired
    UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity<T> getAllUser(){return  ResponseEntity.ok((T) userService.getAllUser());}
    @GetMapping("/getUser/{id}")
    public ResponseEntity<T> getUserById(@PathVariable Long id){return ResponseEntity.ok((T) userService.getUserById(id).getBody());}
    @GetMapping("/getUseByUsername/{username}")
    public ResponseEntity<T> getUserByUsername(@PathVariable String username){return ResponseEntity.ok((T)userService.getByUsername(username).getBody());}
    @PostMapping("/addUser")
    public ResponseEntity<T> addUser(@RequestBody UserDto user){return ResponseEntity.ok((T)userService.addUser(user));}
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserDto user){
        userService.updateUserById(user);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}