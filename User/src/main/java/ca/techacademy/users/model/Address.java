package ca.techacademy.users.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String buildingNumber;
    private String streetName;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String apartmentNumber;
}
