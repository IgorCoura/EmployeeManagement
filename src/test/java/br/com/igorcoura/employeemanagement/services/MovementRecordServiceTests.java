package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.Mapper.MovementRecordMapper;
import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import br.com.igorcoura.employeemanagement.repository.MovementRecordRepository;
import br.com.igorcoura.employeemanagement.services.exception.UpdateMovementRecordException;
import br.com.igorcoura.employeemanagement.utils.MovementRecordUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class MovementRecordServiceTests {

    @Mock
    private MovementRecordRepository movementRecordRepository;

    @Mock
    private  EmployeeRepository employeeRepository;

    @InjectMocks
    private MovementRecordService movementRecordService;


    @Test
    void insertValid() {
        var createModel = MovementRecordUtils.getCreateMovementRecordModelValidAllParameters();
        var entity = MovementRecordUtils.getMovementRecordCloseValidAllParameters();
        var expectedResponse = MovementRecordMapper.toModel(entity);
        Optional<Employee> employee = Optional.of(entity.getEmployee());

        when(movementRecordRepository.save(any(MovementRecord.class))).thenReturn(entity);
        when(employeeRepository.findById(any(Long.class))).thenReturn(employee);
        var response =  movementRecordService.insert(createModel);
        assertEquals(expectedResponse, response);
    }

    @Test
    void updateValidCloseMovementRecord() {
        var expectedResponse = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        Optional<MovementRecord> findReturn = Optional.of(MovementRecordUtils.getMovementRecordOnlyEmployee());
        var returnSave = MovementRecordMapper.toEntity(expectedResponse);

        when(movementRecordRepository.findById(any(Long.class))).thenReturn(findReturn);
        when(movementRecordRepository.save(any(MovementRecord.class))).thenReturn(returnSave);

        var response = movementRecordService.update(expectedResponse, false);

        assertEquals(expectedResponse, response);
    }

    @Test
    void updateValidForceOpenMovementRecord() {
        var expectedResponse = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        var returnSave = MovementRecordMapper.toEntity(expectedResponse);

        when(movementRecordRepository.save(any(MovementRecord.class))).thenReturn(returnSave);

        var response = movementRecordService.update(expectedResponse, true);

        assertEquals(expectedResponse, response);
    }

    @Test
    void updateNotFound() {
        var request = MovementRecordUtils.getMovementRecordModelValidAllParameters();

        assertThrows(EntityNotFoundException.class, () -> {
            movementRecordService.update(request, false);
        });

    }

    @Test
    void updateOpenException() {
        var request = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        Optional<MovementRecord> findReturn = Optional.of(MovementRecordUtils.getMovementRecordOnlyEmployee().builder().isOpen(true).build());

        when(movementRecordRepository.findById(any(Long.class))).thenReturn(findReturn);

        assertThrows(UpdateMovementRecordException.class, () -> {
            movementRecordService.update(request, false);
        });
    }

    @Test
    void recoverAllValid() {
        var listReturn = MovementRecordUtils.createListMovementRecord();
        var expectedResponse = MovementRecordMapper.toListModel(listReturn);

        when(movementRecordRepository.findAll()).thenReturn(listReturn);

        var response = movementRecordService.recoverAll();

        assertEquals(expectedResponse, response);
    }

    @Test
    void recoverByIdValid() {
        Optional<MovementRecord> entity = Optional.of(MovementRecordUtils.getMovementRecordCloseValidAllParameters());
        var expectedResponse = MovementRecordMapper.toModel(entity.get());

        when(movementRecordRepository.findById(any(long.class))).thenReturn(entity);

        var response = movementRecordService.recover(1);

        assertEquals(expectedResponse, response);

    }
}
