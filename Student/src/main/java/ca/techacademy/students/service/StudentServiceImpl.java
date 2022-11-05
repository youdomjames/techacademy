package ca.techacademy.students.service;

import ca.techacademy.students.model.DTO.CredentialsDTO;
import ca.techacademy.students.model.Profile;
import ca.techacademy.students.model.Student;
import ca.techacademy.students.repository.StudentRepository;
import ca.techacademy.students.service.user.UserService;
import ca.techacademy.students.util.enums.Role;
import ca.techacademy.students.util.enums.Status;
import ca.techacademy.students.util.exception.ObjectNotFoundException;
import ca.techacademy.students.util.exception.UserInternalServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final UserService userService;
    private final StudentRepository repository;

    @Override
    public boolean addNewStudent(Profile profile) {
        profile.setStatus(Status.ACTIVE);
        Optional<Profile> optionalProfile = userService.addNewStudentUser(profile, Role.STUDENT)
                .onErrorResume(UserInternalServerException.class, exception -> {
                    log.error("Student password not updated, ERROR = " + exception.getMessage());
                    return Mono.empty();
                })
                .doOnSuccess(success-> {
                    if (success != null) {
                        log.info("Student password successfully updated");
                    }
                })
                .blockOptional();
        if (optionalProfile.isPresent()){
            Student student = Student.builder()
                    .studentId(optionalProfile.get().getProfileId())
                    .build();
            repository.save(student);
            return true;
        }

        return false;
    }

    @Override
    public Profile getStudentProfileById(String studentId) throws ObjectNotFoundException {
          Optional<Profile> optionalProfile = userService.getStudentProfile(studentId)
                .onErrorResume(UserInternalServerException.class, exception -> {
                    log.error("Student not found, ERROR = " + exception.getMessage());
                    return Mono.empty();
                })
                .doOnSuccess(success-> {
                    if (success != null) {
                        log.info("Student found");
                    }
                })
                .blockOptional();
          if(optionalProfile.isEmpty()){
              throw new ObjectNotFoundException("User Profile not found");
          }
          return optionalProfile.get();
    }

    @Override
    public boolean updateStudentProfile(String studentId, Map<String, Object> fields) {
        Optional<Profile> optionalProfile = userService.updateStudentProfile(studentId, fields)
                .onErrorResume(UserInternalServerException.class, exception -> {
                    log.error("Student not found, ERROR = " + exception.getMessage());
                    return Mono.empty();
                })
                .doOnSuccess(success-> {
                    if (success != null) {
                        log.info("Student found");
                    }
                })
                .blockOptional();
        if(optionalProfile.isEmpty()){
            throw new ObjectNotFoundException("User Profile not found");
        }
        return true;
    }

    @Override
    public boolean updateStudentPassword(String studentId, CredentialsDTO credentialsDTO) {
      return Boolean.TRUE.equals(userService.updateStudentPassword(studentId, credentialsDTO)
              .onErrorResume(UserInternalServerException.class, exception -> {
                  log.error("Student password not updated, ERROR = " + exception.getMessage());
                  return Mono.empty();
              })
              .doOnSuccess(success -> {
                  if (success != null) {
                      log.info("Student password updated successfully");
                  }
              })
              .block());
    }


    @Override
    public boolean setStudentInactive(String studentId) {
        return Boolean.TRUE.equals(userService.setStudentInactive(studentId)
                .onErrorResume(UserInternalServerException.class, exception -> {
                    log.error("Student inactivation failed, ERROR = " + exception.getMessage());
                    return Mono.empty();
                })
                .doOnSuccess(success -> {
                    if (success != null) {
                        log.info("Student inactivation completed successfully");
                    }
                })
                .block());
    }


    @Override
    public boolean addStudentToClasses(List<Integer> studentClassIds, String session) {
        return false;
    }

    @Override
    public boolean addStudentWork(String userId, String classId, String assignmentId, File file) {
        return false;
    }

    @Override
    public boolean deleteStudentWork(String userId, String classId, String assignmentId) {
        return false;
    }

    @Override
    public boolean addStudentNewPayment(String studentId, String session) {
        return false;
    }

}
