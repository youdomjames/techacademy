package com.techacademy.courses.service;

import com.techacademy.courses.model.*;
import com.techacademy.courses.model.Class;
import com.techacademy.courses.model.DTO.ClassDTO;
import com.techacademy.courses.repository.ClassRepository;
import com.techacademy.courses.util.enums.Session;
import com.techacademy.courses.util.exception.DuplicateObjectException;
import com.techacademy.courses.util.exception.FieldNotSupportedException;
import com.techacademy.courses.util.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService{

    private final ClassRepository classRepository;

    @Override
    public Class createClass(ClassDTO classDTO) throws DuplicateObjectException {
        Optional<Class> optionalClass = classRepository.findByClassCode(classDTO.getClassCode());
        if (optionalClass.isPresent()){
            throw new DuplicateObjectException("Class already exist");
        }
        Class newClass = Class.builder()
                .classId(UUID.randomUUID().toString())
                .classCode(classDTO.getClassCode())
                .courseId(classDTO.getCourseId())
                .teacherId(classDTO.getTeacherId())
                .session(classDTO.getSession())
                .year(classDTO.getYear())
                .build();
        return classRepository.save(newClass);
    }

    @Override
    public boolean updateClassById(String classId, Map<String, Object> fields) throws ObjectNotFoundException, FieldNotSupportedException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        fields.forEach((key, value)->{
            Field field = null;
            if (key.equals("session")){
                value = Session.valueOf((String) value);
            }else
             field = ReflectionUtils.findField(Class.class, key);
            
            if (field == null) {
                throw new FieldNotSupportedException();
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, foundClass, value);
        });
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public Class getClassById(String classId) throws ObjectNotFoundException {
        return classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public List<Class> getAllClassesByYear(int year) {
        return classRepository.findAllByYear(year);
    }

    @Override
    public List<Class> getAllClassesBySession(Session session) {
        return classRepository.findAllBySession(session);
    }

    @Override
    public List<Assignment> getAllAssignmentsByClassId(String classId) throws ObjectNotFoundException {
        return classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new).getAssignments();
    }

    @Override
    public boolean addNewStudentToClass(String classId, String studentId) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(studentId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getStudentIds().add(studentId);
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean removeStudentFromClass(String classId, String studentId) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getStudentIds().remove(studentId);
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean addNewAssignmentToClass(String classId, Assignment assignment) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getAssignments().add(assignment);
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean updateAssignmentById(String classId, Assignment assignment) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getAssignments().removeIf(assignment1 -> assignment1.getAssignmentCode().equals(assignment.getAssignmentCode()));
        foundClass.getAssignments().add(assignment);
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean removeAssignmentFromClass(String classId, String assignmentCode) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getAssignments().removeIf(assignment1 -> assignment1.getAssignmentCode().equals(assignmentCode));
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean addNewStudentAssignment(String classId, StudentAssignment studentAssignment) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getStudentAssignments().add(studentAssignment);
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean addNewStudentExam(String classId, StudentExam studentExam) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getStudentExams().add(studentExam);
        classRepository.save(foundClass);
        return true;
    }

    @Override
    public boolean updateStudentExam(String classId, StudentExam studentExam) throws ObjectNotFoundException {
        Class foundClass = classRepository.findById(classId).orElseThrow(ObjectNotFoundException::new);
        foundClass.getStudentExams().removeIf(exam -> exam.getExamCode().equals(studentExam.getExamCode()));
        foundClass.getStudentExams().add(studentExam);
        classRepository.save(foundClass);
        return true;
    }
}
