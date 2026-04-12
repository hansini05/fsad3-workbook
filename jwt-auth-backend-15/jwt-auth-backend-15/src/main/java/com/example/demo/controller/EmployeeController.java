package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @GetMapping("/employee/profile")
    public String profile() {
        return "Employee Profile Data";
    }

    @PostMapping("/admin/add")
    public String addEmployee() {
        return "Employee Added by Admin";
    }

    @DeleteMapping("/admin/delete")
    public String deleteEmployee() {
        return "Employee Deleted by Admin";
    }
}