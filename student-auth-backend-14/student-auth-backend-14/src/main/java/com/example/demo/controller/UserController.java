package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    // Register
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        repo.save(user);
        return "User Registered Successfully";
    }

    // Login
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return repo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    // Get Profile
    @GetMapping("/profile/{username}")
    public User getUserProfile(@PathVariable String username) {
        return repo.findByUsername(username);
    }
}