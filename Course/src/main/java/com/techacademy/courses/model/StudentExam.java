package com.techacademy.courses.model;

import com.techacademy.courses.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentExam {
    private String examCode;
    private String studentId;
    private String courseId;
    private Status status;
    private double score;
    private Character grade;
}
