package com.techacademy.courses.controler;


import com.techacademy.courses.model.Course;
import com.techacademy.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> addNewCourseHandler(@RequestBody Course course){
        return new ResponseEntity<>(courseService.addCourse(course), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseByIdHandler(@PathVariable("id") String courseId){
        return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCoursesIdHandler(){
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updateCourseByIdHandler(@PathVariable("id") String courseId, @RequestBody Map<String, Object> fields){
        return new ResponseEntity<>(courseService.updateCourseFields(courseId, fields), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCourseByIdHandler(@PathVariable("id") String courseId){
        return new ResponseEntity<>(courseService.deleteCourseById(courseId), HttpStatus.OK);
    }
}
