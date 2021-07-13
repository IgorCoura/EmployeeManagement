package br.com.igorcoura.employeemanagement.domain.models;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEmployeeModel {

    private String name;
    private String cpf;
    private String empresa;
    private LocalDateTime startWork;
    private LocalDateTime endWork;
    private double lunchTime;

}
