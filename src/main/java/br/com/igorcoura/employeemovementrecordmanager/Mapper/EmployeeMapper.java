package br.com.igorcoura.employeemovementrecordmanager.Mapper;

import br.com.igorcoura.employeemovementrecordmanager.domain.entities.Employee;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.ReturnEmployeeModel;


import java.util.List;
import java.util.stream.Collectors;


public class EmployeeMapper {

    public static Employee toEntity(CreateEmployeeModel employeeModel){
        return Employee.builder()
                .name(employeeModel.getName())
                .cnpj(employeeModel.getCnpj())
                .cpf(employeeModel.getCpf())
                .endWork(employeeModel.getEndWork())
                .startWork(employeeModel.getStartWork())
                .build();
    }

    public static EmployeeModel toModel(Employee employee){
        return new EmployeeModel(employee.getId(), employee.getName(), employee.getCpf(), employee.getCnpj(), employee.getStartWork(), employee.getEndWork());
    }

    public static Employee toEntity(EmployeeModel employeeModel){
        return Employee.builder()
                .id(employeeModel.getId())
                .name(employeeModel.getName())
                .cnpj(employeeModel.getCnpj())
                .cpf(employeeModel.getCpf())
                .endWork(employeeModel.getEndWork())
                .startWork(employeeModel.getStartWork())
                .build();
    }


    public static List<ReturnEmployeeModel> toReturnModel(List<Employee> employees){
        return employees.stream().map(e -> toReturnModel(e)).collect(Collectors.toList());
    }

    public static ReturnEmployeeModel toReturnModel(Employee employee){
        return new ReturnEmployeeModel(employee.getId(), employee.getName(), employee.getCpf(), employee.getCnpj(), MovementRecordMapper.toListModel(employee.getMovementRecord()), employee.getStartWork(), employee.getEndWork());
    }

}
