package ca.techacademy.students.service;

import ca.techacademy.students.model.DTO.CredentialsDTO;
import ca.techacademy.students.model.Profile;
import ca.techacademy.students.repository.StudentRepository;
import ca.techacademy.students.util.enums.Role;
import ca.techacademy.students.util.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface StudentService {
    boolean addNewStudent(Profile profile);
    Profile getStudentProfileById(String studentId);
    boolean updateStudentProfile(String studentId, Map<String, Object> fields) throws ObjectNotFoundException;
    boolean updateStudentPassword(String studentId, CredentialsDTO credentialsDTO);
    boolean setStudentInactive(String studentId);
    boolean addStudentToClasses(List<Integer> studentClassIds, String session);
    boolean addStudentWork(String userId, String classId, String assignmentId, File file);
    boolean deleteStudentWork(String userId, String classId, String assignmentId);
    boolean addStudentNewPayment(String studentId, String session);
}
