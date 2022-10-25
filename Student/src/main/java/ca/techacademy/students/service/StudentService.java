package ca.techacademy.students.service;

import ca.techacademy.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface StudentService {
    boolean addNewStudent(String userId, String profileId);
    boolean addStudentClasses(List<Integer> studentClassIds, String session);
}
