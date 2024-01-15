package org.example.pocpatientmanagement.web;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.pocpatientmanagement.entities.Patient;
import org.example.pocpatientmanagement.mappers.PatientMapper;
import org.example.pocpatientmanagement.repositories.PatientRepository;
import org.example.pocpatientmanagement.stub.PatientServiceGrpc;
import org.example.pocpatientmanagement.stub.PatientServiceOuterClass;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
public class PatientGrpcService extends PatientServiceGrpc.PatientServiceImplBase {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @Override
    public void getAllPatients(
            PatientServiceOuterClass.GetAllPatientsRequest request,
            StreamObserver<PatientServiceOuterClass.GetAllPatientsResponse> responseObserver
    ) {
        List<Patient> patientList = patientRepository.findAll();
        List<PatientServiceOuterClass.Patient> grpcPatients =
                patientList.stream()
                        .map(patientMapper::fromPatientToPatientGrpc).collect(Collectors.toList());
        PatientServiceOuterClass.GetAllPatientsResponse patientsResponse =
                PatientServiceOuterClass.GetAllPatientsResponse.newBuilder()
                        .addAllPatients(grpcPatients)
                        .build();
        responseObserver.onNext(patientsResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getPatientById(
            PatientServiceOuterClass.GetPatientByIdRequest request,
            StreamObserver<PatientServiceOuterClass.GetPatientByIdResponse> responseObserver
    ) {

        Patient patientEntity = patientRepository.findById(request.getPatientId()).get();
        PatientServiceOuterClass.GetPatientByIdResponse response =
                PatientServiceOuterClass.GetPatientByIdResponse.newBuilder()
                        .setPatient(patientMapper.fromPatientToPatientGrpc(patientEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void savePatient(
            PatientServiceOuterClass.SavePatientRequest request,
            StreamObserver<PatientServiceOuterClass.SavePatientResponse> responseObserver
    ) {
        PatientServiceOuterClass.PatientRequest patientRequest = request.getPatient();
        Patient patient = patientMapper.fromGrpcPatientRequestToPatient(patientRequest);
        Patient savedPatient = patientRepository.save(patient);
        PatientServiceOuterClass.SavePatientResponse response =
                PatientServiceOuterClass.SavePatientResponse.newBuilder()
                        .setPatient(patientMapper.fromPatientToPatientGrpc(savedPatient))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
