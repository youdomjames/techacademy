package com.techacademy.courses.model;

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
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    private String assignmentCode;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
   // private File assignmentFile;
    private String teacherComments;
}
