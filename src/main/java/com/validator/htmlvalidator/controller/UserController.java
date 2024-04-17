package com.validator.htmlvalidator.controller;

import com.validator.htmlvalidator.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.validator.htmlvalidator.repositories.UserRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    // standard constructors
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = List.of(new User(1,"Kispal zoltan", "kis@pal.hu"));
        return users;
        //return "user is added";
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
