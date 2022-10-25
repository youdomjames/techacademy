package ca.techacademy.course.model;

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
    private List<String> assignmentIds;
    private List<String> examIds;
    private Session session;
    private Year year;
}
