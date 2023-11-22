package com.marktplatz.marktplatz.controller;

import com.marktplatz.marktplatz.DTOs.UserDto;
import com.marktplatz.marktplatz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping(value = "/auth",method = RequestMethod.POST)
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public ResponseEntity<String> login(){
        //UserDto userdto = userService.getByUsername(user.getUsername()).getBody();
        //return ResponseEntity.ok(userService.login(user).getBody());}
        return ResponseEntity.ok("Hallo");}


    @GetMapping("s")
    public String s(){
        return "Hallo";
    }
}
