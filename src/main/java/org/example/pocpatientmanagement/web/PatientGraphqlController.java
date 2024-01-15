package org.example.pocpatientmanagement.web;

import lombok.AllArgsConstructor;
import org.example.pocpatientmanagement.dtos.PatientDto;
import org.example.pocpatientmanagement.entities.Patient;
import org.example.pocpatientmanagement.mappers.PatientMapper;
import org.example.pocpatientmanagement.repositories.PatientRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientGraphqlController {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @QueryMapping
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    @QueryMapping
    public Patient patientById(@Argument Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException(String.format("Patient %s not found", id));
        return patient;
    }

    @MutationMapping
    public Patient savePatient(@Argument PatientDto patientDto) {
        Patient patient = patientMapper.fromPatientDtoPatient(patientDto);
        return patientRepository.save(patient);
    }
}
