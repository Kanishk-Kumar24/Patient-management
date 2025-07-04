package com.pm.patientservice.Service;

import com.pm.patientservice.DTO.RequestDTO;
import com.pm.patientservice.DTO.ResponseDTO;
import com.pm.patientservice.Exceptions.EmailAlreadyExistsException;
import com.pm.patientservice.Exceptions.PatientNotFoundException;
import com.pm.patientservice.Mapper.PatientMapper;
import com.pm.patientservice.Models.Patient;
import com.pm.patientservice.Repo.PatientRepo;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepo repo;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepo repo,BillingServiceGrpcClient billingServiceGrpcClient) {//dependency injection by springboot
        this.repo = repo;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<ResponseDTO> getPatients() {
        List<Patient> lsPatients = repo.findAll();
        List<ResponseDTO> response = lsPatients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return response;
    }

    public ResponseDTO createPatients(RequestDTO request) {
        if (repo.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + request.getEmail());
        }
        Patient pt = repo.save(PatientMapper.toPatient(request));
        billingServiceGrpcClient.createBillingAccount(pt.getId().toString(),pt.getName(),pt.getEmail());
        return PatientMapper.toDTO(pt);
    }

    public ResponseDTO updatePatients(UUID id, RequestDTO request) {
        Patient pt = repo.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with id "+id)
        );
        if (repo.existsByEmailAndIdNot(request.getEmail(),id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + request.getEmail());
        }
        pt.setName(request.getName());
        pt.setEmail(request.getEmail());
        pt.setAddress(request.getAddress());
        pt.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        Patient patient = repo.save(pt);

        return PatientMapper.toDTO(patient);
    }

    public void deletePatient(UUID id){
        repo.deleteById(id);
    }




}
