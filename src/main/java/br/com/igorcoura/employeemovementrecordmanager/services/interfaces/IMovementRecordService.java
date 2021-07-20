package br.com.igorcoura.employeemovementrecordmanager.services.interfaces;

import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.MovementRecordModel;

import java.util.List;

public interface IMovementRecordService {

    public MovementRecordModel insert(CreateMovementRecordModel movementRecordModel);

    public MovementRecordModel update(MovementRecordModel movementRecordModel, boolean forceUpdate);

    public List<MovementRecordModel> recoverAll();

    public MovementRecordModel recover(long id);

    public void delete(long id);
}
