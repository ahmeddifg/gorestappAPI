package com.gorestapp.gorest.controllers;

import com.gorestapp.gorest.integration.responseModel.UserApiResponse;
import com.gorestapp.gorest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private UserApiResponse loadAllUsers() {
        return this.userService.loadAllUsers();
    }
}
