package ca.techacademy.students.controler;

import ca.techacademy.students.model.DTO.CredentialsDTO;
import ca.techacademy.students.model.Profile;
import ca.techacademy.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Boolean> addStudentHandler(@RequestBody Profile profile){
        return new ResponseEntity<>(studentService.addNewStudent(profile), HttpStatus.CREATED);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getStudentProfileById(@PathVariable("id") String studentId){
        return new ResponseEntity<>(studentService.getStudentProfileById(studentId), HttpStatus.OK);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Boolean> updateStudentProfileById(@PathVariable("id") String studentId, @RequestBody Map<String, Object> fields){
        return new ResponseEntity<>(studentService.updateStudentProfile(studentId, fields), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateStudentPasswordHandler(@PathVariable("id") String studentId, @RequestBody CredentialsDTO credentialsDTO){
        return new ResponseEntity<>(studentService.updateStudentPassword(studentId, credentialsDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}/inactivate")
    public ResponseEntity<Boolean> inactivateStudentHandler(@PathVariable("id") String studentId){
        return new ResponseEntity<>(studentService.setStudentInactive(studentId), HttpStatus.OK);
    }
}
