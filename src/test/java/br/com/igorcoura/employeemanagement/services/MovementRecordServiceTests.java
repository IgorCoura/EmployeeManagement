package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.NewMovementRecordModel;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import br.com.igorcoura.employeemanagement.repository.MovementRecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class MovementRecordServiceTests {

    @Mock
    private MovementRecordRepository movementRecordRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private MovementRecordService movementRecordService;

    @Test
    void givenValidNewMovementRecordThenReturnMovementRecord() {
        var newMovementRecord = new NewMovementRecordModel(51, LocalDateTime.now());
        var employee = new Employee(1, "Jose", "123456789", "JoseLTDa", LocalDateTime.MIN, LocalDateTime.MAX, 1);
        var movementRecord = MovementRecord.builder()
                .employee(employee)
                .startTimeWork(newMovementRecord.getDate())
                .isOpen(true).build();

        when(movementRecordRepository.findAll(Example.of(MovementRecord.builder().isOpen(true).build()))).thenReturn(new ArrayList<MovementRecord>());
        when(employeeRepository.getById(any(long.class))).thenReturn(employee);
        when(movementRecordRepository.save(any(MovementRecord.class))).thenReturn(movementRecord);

        var response = movementRecordService.insert(newMovementRecord);

        assertTrue(response.equals(movementRecord));
    }


}
