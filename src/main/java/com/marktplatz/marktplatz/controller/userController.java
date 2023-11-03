package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Controller
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class userController{
    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUser(){return ResponseEntity.ok((List<UserDto>) new UserDto().AllUsertoDto(userService.getAllUser()));}
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){return ResponseEntity.ok((UserDto) userService.getUserById(id).getBody());}
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){return ResponseEntity.ok((UserDto)userService.getByUsername(username).getBody());}
    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){return ResponseEntity.ok(((ResponseEntity<UserDto>) userService.addUser(user)).getBody());}
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserDto user){
        userService.updateUserById(user);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}