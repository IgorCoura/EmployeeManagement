package br.com.igorcoura.employeemanagement.services.interfaces;

import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateUniqueMovementRecordModel;

public interface IUniqueMovementRecordService {

    public MovementRecordModel insert(CreateUniqueMovementRecordModel createUniqueMovementRecordModel);
}
