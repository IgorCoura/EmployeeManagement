package br.com.igorcoura.employeemovementrecordmanager.services;

import br.com.igorcoura.employeemovementrecordmanager.Mapper.MovementRecordMapper;
import br.com.igorcoura.employeemovementrecordmanager.domain.entities.MovementRecord;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.CreateUniqueMovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.repository.EmployeeRepository;
import br.com.igorcoura.employeemovementrecordmanager.repository.MovementRecordCustomRepository;
import br.com.igorcoura.employeemovementrecordmanager.repository.MovementRecordRepository;
import br.com.igorcoura.employeemovementrecordmanager.services.interfaces.IUniqueMovementRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UniqueMovementRecordService implements IUniqueMovementRecordService {

    @Autowired
    private MovementRecordRepository movementRecordRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MovementRecordCustomRepository movementRecordCustomRepository;


    public MovementRecordModel insert(CreateUniqueMovementRecordModel createUniqueMovementRecordModel){
        var employee = employeeRepository.findById(createUniqueMovementRecordModel.getIdEmployee()).orElseThrow(() -> new EntityNotFoundException("Employee with id = "+createUniqueMovementRecordModel.getIdEmployee()+", Not Found"));
        var openList = movementRecordCustomRepository.findAll(null,employee,true);
        MovementRecord movement = null;
        if(openList.stream().count() > 0){
            movement = checkMovementValidity(openList, createUniqueMovementRecordModel);
            movement = addMovementExistingRecord(movement, createUniqueMovementRecordModel);
        }
        if(movement == null){
            movement = MovementRecord.builder()
                    .employee(employee)
                    .startTimeWork(createUniqueMovementRecordModel.getDate())
                    .isOpen(true).build();
        }
        return MovementRecordMapper.toModel(movementRecordRepository.save(movement));
    }

    private MovementRecord checkMovementValidity(List<MovementRecord> listMovementRecord, CreateUniqueMovementRecordModel createUniqueMovementRecordModel){

        List<MovementRecord> listValidMovement = new ArrayList<MovementRecord>();

        for(MovementRecord movement: listMovementRecord){
            if(movement.getStartTimeWork() == null){
                movement.setOpen(false);
                movementRecordRepository.save(movement);
                continue;
            }

            if(movement.getEndTimeWork() != null){
                movement.setOpen(false);
                movementRecordRepository.save(movement);
                continue;
            }

            //Calculate date limit
            var dateLimit = movement.getStartTimeWork().plusDays(1);
            var startWork = movement.getEmployee().getStartWork().plusHours(-1);
            var dateTimeLimit = LocalDateTime.of(dateLimit.getYear(), dateLimit.getMonth(), dateLimit.getDayOfMonth(), startWork.getHour(), startWork.getMinute());

            //Check if the new movement is on the date limit
            //if it is not within the date limit, close the current movement.
            if(dateTimeLimit.isBefore(createUniqueMovementRecordModel.getDate()) || movement.getStartTimeWork().isAfter(createUniqueMovementRecordModel.getDate())){
                if(movement.getEndTimeWork() == null){
                    if(movement.getEndLunchTime() != null){
                        movement.setEndLunchTime(movement.getEndLunchTime());
                    }
                    else if(movement.getStartLunchTime() != null){
                        movement.setEndTimeWork(movement.getStartLunchTime());
                    }
                }
                movement.setOpen(false);
                movementRecordRepository.save(movement);
                continue;
            }
            listValidMovement.add(movement);
        }

        MovementRecord validMovement = null;
        boolean option1 = false, option2= false, option3= false;
        //if there is more than one valid case, this will choose the one with the most data filling in and close the rest.
        for(MovementRecord movement: listValidMovement){
            if(movement.getStartLunchTime() != null && !option1){
                if(movement.getEndLunchTime() != null){
                    option1 = true;
                }
                if(validMovement != null){
                    validMovement.setOpen(false);
                    movementRecordRepository.save(validMovement);
                }
                option2 = true;
                validMovement = movement;
            }

            if(movement.getEndLunchTime() != null && !option1 && !option2){
                if(validMovement != null){
                    validMovement.setOpen(false);
                    movementRecordRepository.save(validMovement);
                }
                option3 = true;
                validMovement = movement;
            }
            if(!option1 && !option2 && !option3){
                if(validMovement != null){
                    validMovement.setOpen(false);
                    movementRecordRepository.save(validMovement);
                }
                validMovement = movement;
            }
        }
        return validMovement;
    }

    private MovementRecord addMovementExistingRecord(MovementRecord movementRecord, CreateUniqueMovementRecordModel createUniqueMovementRecordModel){
        if(movementRecord.getEndLunchTime() == null && movementRecord.getStartLunchTime() == null){
            movementRecord.setStartLunchTime(createUniqueMovementRecordModel.getDate());
            return movementRecord;
        }
        else if(movementRecord.getEndLunchTime() == null){
            movementRecord.setEndLunchTime(createUniqueMovementRecordModel.getDate());
            return movementRecord;
        }
        else if(movementRecord.getEndTimeWork() == null){
            movementRecord.setEndTimeWork(createUniqueMovementRecordModel.getDate());
            movementRecord.setOpen(false);
            return movementRecord;
        }
        return null;
    }

}
