package br.com.igorcoura.employeemovementrecordmanager.domain.models.employee;

import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.MovementRecordModel;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnEmployeeModel {
    private long id;
    private String name;
    private String cpf;
    private String cnpj;
    private List<MovementRecordModel> movementRecord;
    private LocalTime startWork;
    private LocalTime endWork;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnEmployeeModel that = (ReturnEmployeeModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(cpf, that.cpf) && Objects.equals(cnpj, that.cnpj) && Objects.equals(movementRecord, that.movementRecord) && Objects.equals(startWork, that.startWork) && Objects.equals(endWork, that.endWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, cnpj, movementRecord, startWork, endWork);
    }
}
