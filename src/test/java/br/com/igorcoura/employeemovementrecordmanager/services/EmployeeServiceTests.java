package br.com.igorcoura.employeemovementrecordmanager.services;

import br.com.igorcoura.employeemovementrecordmanager.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemovementrecordmanager.domain.entities.Employee;
import br.com.igorcoura.employeemovementrecordmanager.repository.EmployeeRepository;
import br.com.igorcoura.employeemovementrecordmanager.utils.EmployeeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void insertValid() {

        var model = EmployeeUtils.getEmployeeValidCreateModel();
        var entity = EmployeeUtils.getEmployeeValid();

        var expectedSavedEmployee = EmployeeMapper.toModel(entity);

        when(employeeRepository.save(any(Employee.class))).thenReturn(entity);

        var response = employeeService.insert(model);

       assertEquals(expectedSavedEmployee, response);
    }

    @Test
    void updateValid() {
        var entity = EmployeeUtils.getEmployeeValid();
        var expectedResponse = EmployeeMapper.toModel(entity);

        when(employeeRepository.save(any(Employee.class))).thenReturn(entity);

        var response = employeeService.update(expectedResponse);

        assertEquals(expectedResponse,response);
    }

    @Test
    void recoverAllValid() {
        var listEntity = EmployeeUtils.getListEmployeeValid();
        var expectedResponse = listEntity.stream().map(e -> EmployeeMapper.toReturnModel(e)).collect(Collectors.toList());

        when(employeeRepository.findAll()).thenReturn(listEntity);

        var response = employeeService.recoverAll();

        assertEquals(expectedResponse,response);
    }

    @Test
    void recoverByIDValid() {
        var entity = EmployeeUtils.getEmployeeValid();
        Optional<Employee> opt = Optional.of(entity);
        var expectedResponse = EmployeeMapper.toReturnModel(entity);

        when(employeeRepository.findById(any(Long.class))).thenReturn(opt);

        var response = employeeService.recover(1);

        assertEquals(expectedResponse,response);
    }




}
