package br.com.igorcoura.employeemanagement.domain.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewMovementRecordModel {
    private long idEmployee;
    private LocalDateTime date;
}
