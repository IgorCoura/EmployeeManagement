package br.com.igorcoura.employeemanagement.domain.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(employee.lunchTime, lunchTime) == 0 && Objects.equals(name, employee.name) && Objects.equals(cpf, employee.cpf) && Objects.equals(empresa, employee.empresa) && Objects.equals(startWork, employee.startWork) && Objects.equals(endWork, employee.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, empresa, startWork, endWork, lunchTime);
    }
}
