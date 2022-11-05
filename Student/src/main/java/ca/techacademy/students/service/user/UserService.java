package ca.techacademy.students.service.user;

import ca.techacademy.students.model.DTO.CredentialsDTO;
import ca.techacademy.students.model.Profile;
import ca.techacademy.students.util.enums.Role;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserService {
    Mono<Profile> addNewStudentUser(Profile profile, Role role);
    Mono<Profile> updateStudentProfile(String userId, Map<String, Object> fields);
    Mono<Boolean> updateStudentPassword(String userId, CredentialsDTO credentialsDTO);
    Mono<Boolean> setStudentInactive(String studentId);
    Mono<Profile> getStudentProfile(String studentId);
}
