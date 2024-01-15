package org.example.pocpatientmanagement;

import org.example.pocpatientmanagement.entities.Patient;
import org.example.pocpatientmanagement.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PocPatientManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocPatientManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(Patient.builder().name("Mohamed").email("mohamed@gmail.com").descriptionDiagnostic("Check-up").build());
            patientRepository.save(Patient.builder().name("Ahmed").email("ahmed@gmail.com").descriptionDiagnostic("Scanner").build());
            patientRepository.save(Patient.builder().name("Ayoub").email("ayoub@gmail.com").descriptionDiagnostic("Medical analysis").build());
            patientRepository.save(Patient.builder().name("Doha").email("doha@gmail.com").descriptionDiagnostic("Check-up").build());
        };
    }
}
