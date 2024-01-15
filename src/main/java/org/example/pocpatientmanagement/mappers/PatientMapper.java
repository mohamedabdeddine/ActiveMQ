package org.example.pocpatientmanagement.mappers;

import org.example.pocpatientmanagement.dtos.PatientDto;
import org.example.pocpatientmanagement.entities.Patient;
import org.example.pocpatientmanagement.stub.PatientServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Patient fromPatientDtoPatient(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public PatientServiceOuterClass.Patient fromPatientToPatientGrpc(Patient patient) {
        PatientServiceOuterClass.Patient patientGrpc = modelMapper.map(patient, PatientServiceOuterClass.Patient.Builder.class).build();
        return patientGrpc;
    }

    public Patient fromGrpcPatientRequestToPatient(PatientServiceOuterClass.PatientRequest patientRequest) {
        System.out.println(patientRequest);
        return modelMapper.map(patientRequest, Patient.class);
    }
}
