package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.Mapper.MovementRecordMapper;
import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import br.com.igorcoura.employeemanagement.repository.MovementRecordRepository;
import br.com.igorcoura.employeemanagement.services.exception.UpdateMovementRecordException;
import br.com.igorcoura.employeemanagement.services.interfaces.IMovementRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class MovementRecordService implements IMovementRecordService {

    @Autowired
    private MovementRecordRepository movementRecordRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public MovementRecordModel insert(CreateMovementRecordModel movementRecordModel){
        var entity = MovementRecordMapper.toEntity(movementRecordModel);
        var employee = employeeRepository.findById(movementRecordModel.getIdEmployee()).orElseThrow(() -> new EntityNotFoundException("Employee with id = "+movementRecordModel.getIdEmployee()+", Not Found"));
        entity.setEmployee(employee);
        return MovementRecordMapper.toModel(movementRecordRepository.save(entity));
    }

    public MovementRecordModel update(MovementRecordModel movementRecordModel, boolean forceUpdate){
        var entity = MovementRecordMapper.toEntity(movementRecordModel);
        var employee = employeeRepository.findById(movementRecordModel.getIdEmployee()).orElseThrow(() -> new EntityNotFoundException("Employee with id = "+movementRecordModel.getIdEmployee()+", Not Found"));
        entity.setEmployee(employee);
        if(!forceUpdate){
            var movementRecord = movementRecordRepository.findById(entity.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Movement Record with id = "+entity.getId()+", Not Found")
            );
            if(movementRecord.isOpen()){
                throw new UpdateMovementRecordException("Open Movement Records cannot be updated. Force update to close and update Movement Record");
            }
        }
        return MovementRecordMapper.toModel(movementRecordRepository.save(entity));
    }

    public List<MovementRecordModel> recoverAll(){
        return MovementRecordMapper.toListModel(movementRecordRepository.findAll());
    }

    public MovementRecordModel recover(long id){
        var entity = movementRecordRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Movement record with id = "+id+", Not Found"));
        return MovementRecordMapper.toModel(entity);
    }

    public void delete(long id){
        movementRecordRepository.deleteById(id);
    }

}
