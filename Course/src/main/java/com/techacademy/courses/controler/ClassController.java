package com.techacademy.courses.controler;

import com.techacademy.courses.model.Assignment;
import com.techacademy.courses.model.Class;
import com.techacademy.courses.model.DTO.ClassDTO;
import com.techacademy.courses.model.StudentAssignment;
import com.techacademy.courses.model.StudentExam;
import com.techacademy.courses.service.ClassService;
import com.techacademy.courses.util.enums.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping
    public ResponseEntity<Class> addNewClassHandler(@RequestBody ClassDTO classDTO){
        return new ResponseEntity<>(classService.createClass(classDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editClassHandler(@PathVariable("id") String classId, @RequestBody Map<String, Object> fields){
        return new ResponseEntity<>(classService.updateClassById(classId, fields), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassHandler(@PathVariable("id") String classId){
        return new ResponseEntity<>(classService.getClassById(classId), HttpStatus.OK);
    }
    @GetMapping("/all/year/{year}")
    public ResponseEntity<List<Class>> getAllClassByYearHandler(@PathVariable("year") int year){
        return new ResponseEntity<>(classService.getAllClassesByYear(year), HttpStatus.OK);
    }
    @GetMapping("/all/session/{session}")
    public ResponseEntity<List<Class>> getAllClassBySessionHandler(@PathVariable("session") Session session){
        return new ResponseEntity<>(classService.getAllClassesBySession(session), HttpStatus.OK);
    }
    @GetMapping("/{id}/assignments")
    public ResponseEntity<List<Assignment>> getAllAssignmentsByClassIdHandler(@PathVariable("id") String id){
        return new ResponseEntity<>(classService.getAllAssignmentsByClassId(id), HttpStatus.OK);
    }
    @PostMapping("/{id}/student/{studentId}")
    public ResponseEntity<Boolean> addNewStudentToClassHandler(@PathVariable("id") String classId, @PathVariable("studentId") String studentId){
        return new ResponseEntity<>(classService.addNewStudentToClass(classId,studentId), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/student/{studentId}")
    public ResponseEntity<Boolean> deleteStudentFromClassHandler(@PathVariable("id") String classId, @PathVariable("studentId") String studentId){
        return new ResponseEntity<>(classService.removeStudentFromClass(classId,studentId), HttpStatus.OK);
    }
    @PostMapping("/{id}/assignment")
    public ResponseEntity<Boolean> addNewAssignmentToClassHandler(@PathVariable("id") String classId, @RequestBody Assignment assignment){
        return new ResponseEntity<>(classService.addNewAssignmentToClass(classId,assignment), HttpStatus.OK);
    }
    @PutMapping("/{id}/assignment")
    public ResponseEntity<Boolean> updateAssignmentHandler(@PathVariable("id") String classId, @RequestBody Assignment assignment){
        return new ResponseEntity<>(classService.addNewAssignmentToClass(classId,assignment), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/assignment/{assignmentCode}")
    public ResponseEntity<Boolean> updateAssignmentHandler(@PathVariable("id") String classId, @PathVariable("assignmentCode") String assignmentCode){
        return new ResponseEntity<>(classService.removeAssignmentFromClass(classId, assignmentCode), HttpStatus.OK);
    }
    @PostMapping("/{id}/student/assignment")
    public ResponseEntity<Boolean> addNewStudentAssignmentHandler(@PathVariable("id") String classId, @RequestBody StudentAssignment studentAssignment){
        return new ResponseEntity<>(classService.addNewStudentAssignment(classId,studentAssignment), HttpStatus.OK);
    }
    @PostMapping("/{id}/student/exam")
    public ResponseEntity<Boolean> addNewStudentExamHandler(@PathVariable("id") String classId, @RequestBody StudentExam studentExam){
        return new ResponseEntity<>(classService.addNewStudentExam(classId,studentExam), HttpStatus.OK);
    }
    @PutMapping("/{id}/student/exam")
    public ResponseEntity<Boolean> updateStudentExamHandler(@PathVariable("id") String classId, @RequestBody StudentExam studentExam){
        return new ResponseEntity<>(classService.updateStudentExam(classId,studentExam), HttpStatus.OK);
    }
}
