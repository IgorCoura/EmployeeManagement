package br.com.igorcoura.employeemanagement.Mapper;

import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.models.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.EmployeeModel;


import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


public class EmployeeMapper {

    public static Employee toEntity(CreateEmployeeModel employeeModel){
        return Employee.builder()
                .name(employeeModel.getName())
                .empresa(employeeModel.getEmpresa())
                .cpf(employeeModel.getCpf())
                .endWork(employeeModel.getEndWork())
                .startWork(employeeModel.getStartWork())
                .build();
    }

    public static EmployeeModel toModel(Employee employee){
        return new EmployeeModel(employee.getId(), employee.getName(), employee.getCpf(), employee.getEmpresa(), employee.getStartWork(), employee.getEndWork());
    }

    public static Employee toEntity(EmployeeModel employeeModel){
        return new Employee(employeeModel.getId(), employeeModel.getName(), employeeModel.getCpf(), employeeModel.getEmpresa(), employeeModel.getStartWork(), employeeModel.getEndWork());
    }

    public static List<EmployeeModel> toModel(List<Employee> employees){
        return employees.stream().map(e -> toModel(e)).collect(Collectors.toList());
    }
}
