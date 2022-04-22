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



    //accept user credentials and verify with local mongo DB ==>> 'UserAuthAccounts' mongo collection
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

    // First i check of the username/email is listed at gorest
    // then will register a new user in with encrypted password on mongo database,
    // the user ID at gorest is linked to the user record at my mongo DB

    // NOTE: i already implemented the add user scenario with gorest (check GorestAPIClient.saveUserAccount)
    // But when i tried to get the same user gorest.io returning the result with 302 status
    // Or the post action went successfuly but no data returned when i try to get the same user by ID
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody @Validated RegisterRequest empAccount) {
        return ResponseEntity.ok(this.jwtUserDetailsService.registerNewUser(empAccount));
    }
}

