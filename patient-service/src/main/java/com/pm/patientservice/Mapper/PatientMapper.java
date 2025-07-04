package com.pm.patientservice.Mapper;

import com.pm.patientservice.DTO.RequestDTO;
import com.pm.patientservice.DTO.ResponseDTO;
import com.pm.patientservice.Models.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static ResponseDTO toDTO(Patient pt){
        ResponseDTO response = new ResponseDTO();
        response.setId(pt.getId().toString());
        response.setName(pt.getName());
        response.setAddress(pt.getAddress());
        response.setEmail(pt.getEmail());
        response.setDateOfBirth(pt.getDateOfBirth().toString());

        return response;
    }
    public static Patient toPatient(RequestDTO request){
        Patient pt = new Patient();
        pt.setName(request.getName());
        pt.setEmail(request.getEmail());
        pt.setAddress(request.getAddress());
        pt.setRegisteredDate(LocalDate.parse(request.getRegisteredDate()));
        pt.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        return pt;
    }
}
