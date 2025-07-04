package com.pm.patientservice.DTO;

import lombok.Data;

@Data
public class ResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;

}
