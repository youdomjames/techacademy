package ca.techacademy.students.model;

import ca.techacademy.students.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tuition {
    private double totalAmount;
    private Status status;
    private List<Payment> payments;
    private String session;

}
