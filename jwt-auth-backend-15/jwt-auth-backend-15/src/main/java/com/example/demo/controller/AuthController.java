package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository repo;

    private JwtUtil jwtUtil = new JwtUtil();

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User u = repo.findByUsername(user.getUsername());

        if (u != null && u.getPassword().equals(user.getPassword())) {
            return jwtUtil.generateToken(u.getUsername(), u.getRole());
        }

        return "Invalid Credentials";
    }
}