package br.com.igorcoura.employeemanagement.domain.models;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateEmployeeModel that = (CreateEmployeeModel) o;
        return Double.compare(that.lunchTime, lunchTime) == 0 && Objects.equals(name, that.name) && Objects.equals(cpf, that.cpf) && Objects.equals(empresa, that.empresa) && Objects.equals(startWork, that.startWork) && Objects.equals(endWork, that.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, empresa, startWork, endWork, lunchTime);
    }
}
