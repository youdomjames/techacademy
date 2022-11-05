package com.techacademy.courses.model;

import com.techacademy.courses.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAssignment {
    @Id
    private String assignmentId;
    private String studentId;
    private String courseId;
    private Status status;
    private double score;
    private boolean emailSentToTeacher;
    private Character grade;
    private File studentWorkFile;
}
