package br.com.igorcoura.employeemovementrecordmanager.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
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
    private String cnpj;
    @OneToMany(fetch = FetchType.EAGER)
    private List<MovementRecord> movementRecord;
    @Column(nullable = false)
    private LocalTime startWork;
    @Column(nullable = false)
    private LocalTime endWork;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(cpf, employee.cpf) && Objects.equals(cnpj, employee.cnpj) && Objects.equals(movementRecord, employee.movementRecord) && Objects.equals(startWork, employee.startWork) && Objects.equals(endWork, employee.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, cnpj, movementRecord, startWork, endWork);
    }
}
