package com.example.employee.service;

import com.example.employee.entity.Course;
import com.example.employee.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class CourseService {

    private final S3Client s3Client;
    private final CourseRepository courseRepository;
    public CourseService(S3Client s3Client, CourseRepository courseRepository) {
        this.s3Client = s3Client;
        this.courseRepository = courseRepository;
    }
    @Value("${aws.bucket-name}")
    private String bucketName;

    public Course saveCourse(String courseName, String duration, Double price, MultipartFile image) throws IOException {

        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .contentType(image.getContentType())
                        .build(),
                RequestBody.fromBytes(image.getBytes())
        );

        String imageUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

        Course course = new Course();
        course.setCourseName(courseName);
        course.setDuration(duration);
        course.setPrice(price);
        course.setImageUrl(imageUrl);
        return courseRepository.save(course);
    }
}
