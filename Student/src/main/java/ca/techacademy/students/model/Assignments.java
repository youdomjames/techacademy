package ca.techacademy.students.model;

import ca.techacademy.students.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assignments {
    private String assignmentId;
    private LocalDateTime completedDate;
    private boolean isWorkSentToTeacher;
    private File workFile;
}
