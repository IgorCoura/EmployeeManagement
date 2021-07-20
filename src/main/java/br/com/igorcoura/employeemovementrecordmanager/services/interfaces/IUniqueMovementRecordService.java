package br.com.igorcoura.employeemovementrecordmanager.services.interfaces;

import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.CreateUniqueMovementRecordModel;

public interface IUniqueMovementRecordService {

    public MovementRecordModel insert(CreateUniqueMovementRecordModel createUniqueMovementRecordModel);
}
