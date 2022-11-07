package com.techacademy.courses.repository;

import com.techacademy.courses.model.Class;
import com.techacademy.courses.util.enums.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends MongoRepository<Class, String> {
    Optional<Class> findByClassCode(String classCode);
    List<Class> findAllByYear(int year);
    List<Class> findAllBySession(Session session);
}
