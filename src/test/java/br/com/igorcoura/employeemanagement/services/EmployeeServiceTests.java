package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.models.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void givenValidCreateEmployeeModelThenReturnEmployeeModel() {

        var model = new CreateEmployeeModel("Jose", "123456789", "JoseLTDa", LocalTime.MAX, LocalTime.MAX);
        var entity = new Employee(1, "Jose", "123456789", "JoseLTDa", LocalTime.MAX, LocalTime.MAX);

        var expectedSavedEmployee = EmployeeMapper.toModel(entity);

        when(employeeRepository.save(any(Employee.class))).thenReturn(entity);

        var response = employeeService.insert(model);

        Assertions.assertTrue(response.equals(expectedSavedEmployee));
    }

    @Test
    void givenInvalidCreateEmployeeModelThenReturnEmployeeModel() {

        var model = new CreateEmployeeModel(null, "123456789", "JoseLTDa", LocalTime.MAX, LocalTime.MAX);
        var entity = new Employee(1, "Jose", "123456789", "JoseLTDa", LocalTime.MAX, LocalTime.MAX);

        var expectedSavedEmployee = EmployeeMapper.toModel(entity);

        when(employeeRepository.save(any(Employee.class))).thenReturn(entity);

        var response = employeeService.insert(model);

        Assertions.assertTrue(response.equals(expectedSavedEmployee));
    }


}
