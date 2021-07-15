package br.com.igorcoura.employeemanagement.domain.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String empresa;
    @Column(nullable = false)
    private LocalTime startWork;
    @Column(nullable = false)
    private LocalTime endWork;

    @Column(nullable = false)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(cpf, employee.cpf) && Objects.equals(empresa, employee.empresa) && Objects.equals(startWork, employee.startWork) && Objects.equals(endWork, employee.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, empresa, startWork, endWork);
    }
}
