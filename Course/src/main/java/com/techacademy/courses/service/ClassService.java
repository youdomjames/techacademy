package com.techacademy.courses.service;

import com.techacademy.courses.model.Assignment;
import com.techacademy.courses.model.Class;
import com.techacademy.courses.model.DTO.ClassDTO;
import com.techacademy.courses.model.StudentAssignment;
import com.techacademy.courses.model.StudentExam;
import com.techacademy.courses.util.enums.Session;
import com.techacademy.courses.util.exception.DuplicateObjectException;
import com.techacademy.courses.util.exception.FieldNotSupportedException;
import com.techacademy.courses.util.exception.ObjectNotFoundException;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface ClassService {
    Class createClass(ClassDTO classDTO) throws DuplicateObjectException;
    boolean updateClassById(String classId, Map<String, Object> fields) throws ObjectNotFoundException, FieldNotSupportedException;
    Class getClassById(String classId) throws ObjectNotFoundException;
    List<Class> getAllClassesByYear(int year);
    List<Class> getAllClassesBySession(Session session);
    List<Assignment> getAllAssignmentsByClassId(String classId) throws ObjectNotFoundException;
    boolean addNewStudentToClass(String classId, String studentId)throws ObjectNotFoundException;
    boolean removeStudentFromClass(String classId, String studentId) throws  ObjectNotFoundException;
    boolean addNewAssignmentToClass(String classId, Assignment assignment) throws ObjectNotFoundException;
    boolean updateAssignmentById(String classId, Assignment assignment)throws ObjectNotFoundException;
    boolean removeAssignmentFromClass(String classId, String assignmentCode) throws ObjectNotFoundException;
    boolean addNewStudentAssignment(String classId, StudentAssignment studentAssignment)throws ObjectNotFoundException;
    boolean addNewStudentExam(String classId, StudentExam studentExam) throws ObjectNotFoundException;
    boolean updateStudentExam(String classId, StudentExam studentExam) throws ObjectNotFoundException;
}
