package br.com.igorcoura.employeemanagement.domain.models;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeModel {
    private long id;
    private String name;
    private String cpf;
    private String empresa;
    private LocalDateTime startWork;
    private LocalDateTime endWork;
    private double lunchTime;
}
