package ca.techacademy.students.service.course;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Override
    public HttpStatus addStudentToClasses(String studentId, List<String> classId) {
        return null;
    }

    @Override
    public HttpStatus addStudentAssignment(String studentId, String classId, String assignmentId, File file) {
        return null;
    }

    @Override
    public HttpStatus deleteStudentAssignment(String studentId, String classId, String assignmentId) {
        return null;
    }
}
