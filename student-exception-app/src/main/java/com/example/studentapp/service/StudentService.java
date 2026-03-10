package com.example.studentapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.studentapp.model.Student;
import com.example.studentapp.exception.StudentNotFoundException;

@Service
public class StudentService {

    private static Map<Integer, Student> students = new HashMap<>();

    static {
        students.put(1, new Student(1, "Ravi", "CSE"));
        students.put(2, new Student(2, "Anitha", "ECE"));
    }

    public Student getStudentById(int id) {

        Student student = students.get(id);

        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }

        return student;
    }
}