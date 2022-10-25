package ca.techacademy.students.repository;

import ca.techacademy.students.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
