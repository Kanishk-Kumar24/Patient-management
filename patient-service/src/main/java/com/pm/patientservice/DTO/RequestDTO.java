package com.pm.patientservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100,message = "name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "email is req")
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class)
    private String registeredDate;

}
