package ca.techacademy.students.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private String examId;
    private String courseId;
    private LocalDateTime examPassedDate;
    private double note;
    private char grade;
    private String teacherComment;
}
