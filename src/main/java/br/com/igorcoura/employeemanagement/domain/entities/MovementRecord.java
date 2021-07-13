package br.com.igorcoura.employeemanagement.domain.entities;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @ManyToOne
    private Employee employee;
    private LocalDateTime startWork;
    private LocalDateTime startLunchTime;
    private LocalDateTime endLunchTime;
    private LocalDateTime endWork;
}
