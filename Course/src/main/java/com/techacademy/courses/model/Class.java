package com.techacademy.courses.model;

import com.techacademy.courses.util.enums.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Year;
import java.util.List;

@Data
@Builder

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
    @NotNull
    private Session session;
    private Year year;
}
