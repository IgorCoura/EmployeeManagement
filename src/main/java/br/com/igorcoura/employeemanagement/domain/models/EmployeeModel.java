package br.com.igorcoura.employeemanagement.domain.models;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


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
    private LocalTime startWork;
    private LocalTime endWork;
    private double lunchTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel that = (EmployeeModel) o;
        return id == that.id && Double.compare(that.lunchTime, lunchTime) == 0 && Objects.equals(name, that.name) && Objects.equals(cpf, that.cpf) && Objects.equals(empresa, that.empresa) && Objects.equals(startWork, that.startWork) && Objects.equals(endWork, that.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, empresa, startWork, endWork, lunchTime);
    }
}
