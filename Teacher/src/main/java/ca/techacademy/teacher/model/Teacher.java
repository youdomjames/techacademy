package ca.techacademy.teacher.model;

import ca.techacademy.teacher.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String teacherId;
    private double salary;
    private LocalDate firstDateOfWork;
    private Status status;
}
