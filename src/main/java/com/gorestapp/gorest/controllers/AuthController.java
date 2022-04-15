package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.model.response.AuthKey;
import com.gorestapp.gorest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<AuthKey> login(@RequestHeader( name = "email",required = true) String email,
                                         @RequestHeader( name = "userId",required = true) Integer userId              )  {
        return ResponseEntity.ok(this.userService.login(email,userId));
    }
}
