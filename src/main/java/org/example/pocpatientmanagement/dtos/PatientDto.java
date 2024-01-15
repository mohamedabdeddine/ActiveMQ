package org.example.pocpatientmanagement.dtos;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class PatientDto {
    private long id;
    private String name;
    private String email;
    private String descriptionDiagnostic;
}
