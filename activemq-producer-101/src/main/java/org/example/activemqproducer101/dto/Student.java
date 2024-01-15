package org.example.activemqproducer101.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Student implements Serializable {

    private String studentId;
    private String name;
    private String rollNumber;
}
