package com.example.course.service;

import com.example.course.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private List<Course> courseList = new ArrayList<>();

    // Add course
    public Course addCourse(Course course) {
        courseList.add(course);
        return course;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseList;
    }

    // Get course by ID
    public Course getCourseById(int id) {
        return courseList.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update course
    public Course updateCourse(int id, Course updatedCourse) {
        Course course = getCourseById(id);

        if (course != null) {
            course.setTitle(updatedCourse.getTitle());
            course.setDuration(updatedCourse.getDuration());
            course.setFee(updatedCourse.getFee());
        }

        return course;
    }

    // Delete course
    public boolean deleteCourse(int id) {
        Course course = getCourseById(id);
        if (course != null) {
            courseList.remove(course);
            return true;
        }
        return false;
    }

    // Search by title
    public List<Course> searchByTitle(String title) {
        List<Course> result = new ArrayList<>();

        for (Course c : courseList) {
            if (c.getTitle().equalsIgnoreCase(title)) {
                result.add(c);
            }
        }

        return result;
    }
}