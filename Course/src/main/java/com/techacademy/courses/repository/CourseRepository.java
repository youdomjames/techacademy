package com.techacademy.courses.repository;

import com.techacademy.courses.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
}
