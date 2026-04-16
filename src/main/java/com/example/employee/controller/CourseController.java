package com.example.employee.controller;



import com.example.employee.entity.Course;
import com.example.employee.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(consumes = "multipart/form-data")
    public Course createCourse(
            @RequestParam String courseName,
            @RequestParam String duration,
            @RequestParam Double price,
            @RequestParam MultipartFile image
    ) throws Exception {
        return courseService.saveCourse(courseName, duration, price, image);
    }

    // ✅ Dummy test endpoint
    @GetMapping("/test")
    public String testApi() {
        return "Course API is working fine 🚀";
    }

    // ✅ Dummy health check
    @GetMapping("/health")
    public String healthCheck() {
        return "Application is UP ✅";
    }
}