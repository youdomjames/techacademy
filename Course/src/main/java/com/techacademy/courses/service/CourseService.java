package com.techacademy.courses.service;

import com.techacademy.courses.model.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Course addCourse(Course course);
    Course getCourseById(String courseId);
    List<Course> getAllCourses();
    boolean updateCourseFields(String courseId, Map<String, Object> fields);
    boolean deleteCourseById(String courseId);
}
