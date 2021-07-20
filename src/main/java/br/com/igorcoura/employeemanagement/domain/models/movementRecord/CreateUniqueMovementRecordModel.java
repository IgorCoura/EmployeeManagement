package br.com.igorcoura.employeemanagement.domain.models.movementRecord;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUniqueMovementRecordModel {
    @NotNull(message = "Id emplyee cannot be null")
    private long idEmployee;
    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;
}
