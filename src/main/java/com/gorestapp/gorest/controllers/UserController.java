package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import com.gorestapp.gorest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    private ResponseEntity loadAllUsers(@RequestParam(name="page", required = false) Integer page) {
        return ResponseEntity.ok(this.userService.loadAllUsers(page));
    }
}
