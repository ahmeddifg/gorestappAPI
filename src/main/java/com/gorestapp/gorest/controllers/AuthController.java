package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.authconfig.JwtResponse;
import com.gorestapp.gorest.authconfig.JwtUserDetailsService;
import com.gorestapp.gorest.entities.UserAuthAccount;
import com.gorestapp.gorest.exceptions.exceptionTypes.LoginException;
import com.gorestapp.gorest.model.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
        if(!this.jwtUserDetailsService.validatePassword(password,userAuthAccount.getPassword())){
            throw new LoginException();
        }
        return ResponseEntity.ok( new JwtResponse(jwtUserDetailsService.generateTokenFromUserDetails(userAuthAccount)));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Validated RegisterRequest empAccount) {
        return ResponseEntity.ok(this.jwtUserDetailsService.registerNewUser(empAccount));
    }
}

