package com.example.studentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import com.example.studentapp.exception.InvalidInputException;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        try {
            int studentId = Integer.parseInt(id);
            return studentService.getStudentById(studentId);

        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid student ID format. ID must be a number.");
        }
    }
}