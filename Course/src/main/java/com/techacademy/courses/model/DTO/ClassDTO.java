package com.techacademy.courses.model.DTO;

import com.techacademy.courses.util.enums.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private String classCode;
    private String courseId;
    private String teacherId;
    private Session session;
    private int year;
}
