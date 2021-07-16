package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import br.com.igorcoura.employeemanagement.utils.EmployeeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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

        Assertions.assertTrue(response.equals(expectedSavedEmployee));
    }

    @Test
    void updateValid() {
        var entity = EmployeeUtils.getEmployeeValid();
        var expectedResponse = EmployeeMapper.toModel(entity);

        when(employeeRepository.save(any(Employee.class))).thenReturn(entity);

        var response = employeeService.update(expectedResponse);

        Assertions.assertTrue(response.equals(expectedResponse));
    }

    @Test
    void recoverAllValid() {
        var listEntity = EmployeeUtils.getListEmployeeValid();
        var expectedResponse = listEntity.stream().map(e -> EmployeeMapper.toModel(e)).collect(Collectors.toList());

        when(employeeRepository.findAll()).thenReturn(listEntity);

        var response = employeeService.recoverAll();

        Assertions.assertTrue(response.equals(expectedResponse));
    }

    @Test
    void recoverByIDValid() {
        var entity = EmployeeUtils.getEmployeeValid();
        Optional<Employee> opt = Optional.of(entity);
        var expectedResponse = EmployeeMapper.toModel(entity);

        when(employeeRepository.findById(any(Long.class))).thenReturn(opt);

        var response = employeeService.recover(1);

        Assertions.assertTrue(response.equals(expectedResponse));
    }


}
