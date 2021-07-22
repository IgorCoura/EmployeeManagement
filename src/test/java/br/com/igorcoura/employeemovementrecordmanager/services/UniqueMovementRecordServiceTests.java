package br.com.igorcoura.employeemovementrecordmanager.services;

import br.com.igorcoura.employeemovementrecordmanager.Mapper.MovementRecordMapper;
import br.com.igorcoura.employeemovementrecordmanager.domain.entities.Employee;
import br.com.igorcoura.employeemovementrecordmanager.domain.entities.MovementRecord;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.repository.EmployeeRepository;
import br.com.igorcoura.employeemovementrecordmanager.repository.MovementRecordCustomRepository;
import br.com.igorcoura.employeemovementrecordmanager.repository.MovementRecordRepository;
import br.com.igorcoura.employeemovementrecordmanager.utils.MovementRecordUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class UniqueMovementRecordServiceTests {

    @Mock
    private MovementRecordRepository movementRecordRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private MovementRecordCustomRepository movementRecordCustomRepository;

    @InjectMocks
    private UniqueMovementRecordService movementRecordService;



    @Test
    void insertValidWhenNotHaveAnythingInMovementRecordRepository() {
        var newMovementRecord = MovementRecordUtils.getNewMovementRecordValidTimeToStartWork();
        var employee = MovementRecordUtils.getEmployee();
        var entity = MovementRecord.builder()
                .employee(employee)
                .startTimeWork(newMovementRecord.getDate())
                .isOpen(true).build();
        MovementRecordModel expectedResponse = MovementRecordMapper.toModel(entity);

        when(movementRecordCustomRepository.findAll(eq(null),any(Employee.class), any(Boolean.class))).thenReturn(new ArrayList<MovementRecord>());
        when(employeeRepository.findById(any(long.class))).thenReturn(Optional.of(employee));
        when(movementRecordRepository.save(any(MovementRecord.class))).thenReturn(entity);

        var response = movementRecordService.insert(newMovementRecord);

        assertEquals(expectedResponse,response);
    }

    @Test
    void insertValidWhenHaveDataInMovementRecordRepository(){
        var newMovementRecord = MovementRecordUtils.getNewMovementRecordValidTimeToEndLunch();
        var employee = MovementRecordUtils.getEmployee();
        var listMovementRecord = MovementRecordUtils.getListMovementRecord();

        MovementRecord entity = new MovementRecord().builder()
                .id(listMovementRecord.get(5).getId())
                .employee(listMovementRecord.get(5).getEmployee())
                .startTimeWork(listMovementRecord.get(5).getStartTimeWork())
                .startLunchTime(listMovementRecord.get(5).getStartLunchTime())
                .endLunchTime(newMovementRecord.getDate())
                .isOpen(true)
                .build();

        MovementRecordModel expectedResponse = MovementRecordMapper.toModel(entity);

        when(movementRecordCustomRepository.findAll(eq(null),any(Employee.class), any(Boolean.class))).thenReturn(listMovementRecord);
        when(employeeRepository.findById(any(long.class))).thenReturn(Optional.of(employee));
        when(movementRecordRepository.save(any(MovementRecord.class))).thenReturn(entity);

        var response = movementRecordService.insert(newMovementRecord);

        assertEquals(expectedResponse, response);
    }
}
