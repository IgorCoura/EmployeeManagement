package br.com.igorcoura.employeemanagement.services.interfaces;

import br.com.igorcoura.employeemanagement.Mapper.MovementRecordMapper;
import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.NewUniqueMovementRecordModel;
import org.springframework.data.domain.Example;

public interface IUniqueMovementRecordService {

    public MovementRecordModel insert(NewUniqueMovementRecordModel newUniqueMovementRecordModel);
}
