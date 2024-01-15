package org.example.pocpatientmanagement.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.example.pocpatientmanagement.dtos.PatientDto;
import org.example.pocpatientmanagement.entities.Patient;
import org.example.pocpatientmanagement.mappers.PatientMapper;
import org.example.pocpatientmanagement.repositories.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService
public class PatientSoapService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @WebMethod
    public Patient patientById(@WebParam(name = "id") Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException(String.format("Patient %s not found", id));
        return patient;
    }

    @WebMethod
    private Patient savePatient(@WebParam(name = "patient") PatientDto patientDto) {
        Patient patient = patientMapper.fromPatientDtoPatient(patientDto);
        return patientRepository.save(patient);
    }
}
