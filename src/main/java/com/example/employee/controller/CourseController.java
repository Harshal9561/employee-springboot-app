package com.example.employee.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {


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