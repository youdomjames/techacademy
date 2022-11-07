package com.techacademy.courses.model;

import com.techacademy.courses.util.enums.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;
import java.util.List;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @Id
    private String classId;
    private String classCode;
    private String courseId;
    private String teacherId;
    private List<String> studentIds;
    private List<Assignment> assignments;
    private List<StudentAssignment> studentAssignments;
    private List<StudentExam> studentExams;
    private Session session;
    private int year;
}
