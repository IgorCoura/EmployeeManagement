package br.com.igorcoura.employeemanagement.services.interfaces;

import br.com.igorcoura.employeemanagement.Mapper.MovementRecordMapper;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.services.exception.UpdateMovementRecordException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IMovementRecordService {

    public MovementRecordModel insert(CreateMovementRecordModel movementRecordModel);

    public MovementRecordModel update(MovementRecordModel movementRecordModel, boolean forceUpdate);

    public List<MovementRecordModel> recoverAll();

    public MovementRecordModel recover(long id);

    public void delete(long id);
}
