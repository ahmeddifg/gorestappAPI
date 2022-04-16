package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.authconfig.JwtResponse;
import com.gorestapp.gorest.authconfig.JwtUserDetailsService;
import com.gorestapp.gorest.authconfig.UserAuthAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;



    @GetMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestHeader(name = "email", required = true) String email,
                                             @RequestHeader(name = "password", required = true) String password) throws Exception {
        UserAuthAccount userAuthAccount = jwtUserDetailsService
                .loadUserByUsername(email);

        return ResponseEntity.ok( new JwtResponse(jwtUserDetailsService.generateTokenFromUserDetails(userAuthAccount)));
    }
}

