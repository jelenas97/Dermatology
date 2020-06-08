package com.dermatology.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String symptoms;
}
