package ca.techacademy.students.model;

import ca.techacademy.students.util.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable {
    private String profileId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Status status;
    private String email;
    private String telephoneNumber;
    private Address address;
}
