package com.pm.patientservice.Controller;

import com.pm.patientservice.DTO.CreatePatientValidationGroup;
import com.pm.patientservice.DTO.RequestDTO;
import com.pm.patientservice.DTO.ResponseDTO;
import com.pm.patientservice.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "API for managing Patients ")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "get call to get a list of all patients")
    public ResponseEntity<List<ResponseDTO>> getPatients() {
        List<ResponseDTO> patients = service.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    @Operation(summary = "post call for creating patient")
    public ResponseEntity<ResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody RequestDTO request) {
        ResponseDTO response = service.createPatients(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "put call to update a patient")
    public ResponseEntity<ResponseDTO> updatePatient(@PathVariable UUID id,
                                                     @Validated(Default.class) @RequestBody RequestDTO request) {
        ResponseDTO response = service.updatePatients(id, request);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete call for deleting a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
