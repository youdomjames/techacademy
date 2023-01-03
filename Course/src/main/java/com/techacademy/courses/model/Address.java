package ca.techacademy.students.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private String buildingNumber;
    private String streetName;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String apartmentNumber;
}
