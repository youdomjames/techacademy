package ca.techacademy.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.time.LocalDateTime;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    private String assignmentId;
    private String classId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
    private File assignmentFile;
    private String teacherComments;
}
