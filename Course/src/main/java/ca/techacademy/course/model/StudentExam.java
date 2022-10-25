package ca.techacademy.course.model;

import ca.techacademy.course.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class StudentExam {
    @Id
    private String examId;
    private String studentId;
    private String courseId;
    private Status status;
    private double score;
    private Character grade;
}
