package com.techacademy.courses.service;


import com.techacademy.courses.model.Course;
import com.techacademy.courses.repository.CourseRepository;
import com.techacademy.courses.util.exception.FieldNotSupportedException;
import com.techacademy.courses.util.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course addCourse(Course course) {
        course.setCourseId(UUID.randomUUID().toString());
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public boolean updateCourseFields(String courseId, Map<String, Object> fields) {
        Course course = courseRepository.findById(courseId).orElseThrow(ObjectNotFoundException::new);
        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Course.class, key);
            if (field == null) {
                throw new FieldNotSupportedException();
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, course, value);
        });
        courseRepository.save(course);
        return true;
    }

    @Override
    public boolean deleteCourseById(String courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(ObjectNotFoundException::new);
        courseRepository.deleteById(course.getCourseId());
        return courseRepository.findById(courseId).isEmpty();
    }
}
