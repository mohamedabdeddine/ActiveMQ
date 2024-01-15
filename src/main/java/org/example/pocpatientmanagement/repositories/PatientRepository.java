package org.example.pocpatientmanagement.repositories;

import org.example.pocpatientmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
