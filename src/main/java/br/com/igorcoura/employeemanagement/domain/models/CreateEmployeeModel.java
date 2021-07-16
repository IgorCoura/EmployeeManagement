package br.com.igorcoura.employeemanagement.domain.models;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEmployeeModel {

    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "CPF cannot be null")
    @Size(min = 11, max = 11, message = "Cpf must have 11 numbers")
    private String cpf;
    @NotBlank(message = "Empresa cannot be null")
    private String empresa;
    @NotNull(message = "Start work cannot be null")
    private LocalTime startWork;
    @NotNull(message = "End work cannot be null")
    private LocalTime endWork;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateEmployeeModel that = (CreateEmployeeModel) o;
        return Objects.equals(name, that.name) && Objects.equals(cpf, that.cpf) && Objects.equals(empresa, that.empresa) && Objects.equals(startWork, that.startWork) && Objects.equals(endWork, that.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, empresa, startWork, endWork);
    }
}
