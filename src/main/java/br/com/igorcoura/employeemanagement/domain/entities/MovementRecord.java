package br.com.igorcoura.employeemanagement.domain.entities;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;
    private LocalDateTime startTimeWork = null;
    private LocalDateTime startLunchTime = null;
    private LocalDateTime endLunchTime = null;
    private LocalDateTime endTimeWork = null;
    private boolean isOpen;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementRecord that = (MovementRecord) o;
        return id == that.id && isOpen == that.isOpen && Objects.equals(employee, that.employee) && Objects.equals(startTimeWork, that.startTimeWork) && Objects.equals(startLunchTime, that.startLunchTime) && Objects.equals(endLunchTime, that.endLunchTime) && Objects.equals(endTimeWork, that.endTimeWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, startTimeWork, startLunchTime, endLunchTime, endTimeWork, isOpen);
    }
}
