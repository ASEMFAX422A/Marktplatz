package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.entity.User;
import com.marktplatz.marktplatz.security.TokenResponse;
import com.marktplatz.marktplatz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth/user", method = RequestMethod.POST)
public class userController{
    @Autowired
    UserService userService;


    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUser(){return ResponseEntity.ok(new UserDto().AllUsertoDto(userService.getAllUser()));}
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){return ResponseEntity.ok( userService.getUserById(id).getBody());}
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){return ResponseEntity.ok(userService.getByUsername(username).getBody());}
    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){return ResponseEntity.ok((userService.addUser(user)).getBody());}
    @PutMapping("/updateUser")
    public void updateUser(@RequestBody UserDto user){
        userService.updateUserById(user);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody User user){

        return ResponseEntity.ok(userService.login(user));
    }

}