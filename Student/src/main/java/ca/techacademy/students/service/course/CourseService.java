package ca.techacademy.students.service.course;

import org.springframework.http.HttpStatus;

import java.io.File;
import java.util.List;

public interface CourseService {
    HttpStatus addStudentToClasses(String studentId, List<String> classId);
    HttpStatus addStudentAssignment(String studentId, String classId, String assignmentId, File file);
    HttpStatus deleteStudentAssignment(String studentId, String classId, String assignmentId);
}
