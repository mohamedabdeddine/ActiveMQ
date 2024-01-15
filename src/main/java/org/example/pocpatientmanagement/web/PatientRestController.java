package org.example.pocpatientmanagement.web;

import lombok.AllArgsConstructor;
import org.example.pocpatientmanagement.dtos.PatientDto;
import org.example.pocpatientmanagement.entities.Patient;
import org.example.pocpatientmanagement.mappers.PatientMapper;
import org.example.pocpatientmanagement.repositories.PatientRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class PatientRestController {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @GetMapping("/patients")
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient patientById(@Argument Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException(String.format("Patient %s not found", id));
        return patient;
    }

    @PostMapping("/patients")
    public Patient savePatient(@RequestBody PatientDto patientDto) {
        Patient patient = patientMapper.fromPatientDtoPatient(patientDto);
        return patientRepository.save(patient);
    }
}
