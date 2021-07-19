package br.com.igorcoura.employeemanagement.domain.models.movementRecord;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMovementRecordModel {

    @NotNull(message = "idEmployee cannot be null")
    private long idEmployee;
    @NotNull(message = "Start time work cannot be null")
    private LocalDateTime startTimeWork = null;
    @NotNull(message = "Start lunch time cannot be null")
    private LocalDateTime startLunchTime = null;
    @NotNull(message = "End lunch time cannot be null")
    private LocalDateTime endLunchTime = null;
    @NotNull(message = "End time work cannot be null")
    private LocalDateTime endTimeWork = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMovementRecordModel that = (CreateMovementRecordModel) o;
        return idEmployee == that.idEmployee && Objects.equals(startTimeWork, that.startTimeWork) && Objects.equals(startLunchTime, that.startLunchTime) && Objects.equals(endLunchTime, that.endLunchTime) && Objects.equals(endTimeWork, that.endTimeWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, startTimeWork, startLunchTime, endLunchTime, endTimeWork);
    }
}
