package br.com.igorcoura.employeemanagement.utils;

import br.com.igorcoura.employeemanagement.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.models.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.EmployeeModel;
import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class EmployeeUtils {


    @Getter
    private static Employee employeeValid = new Employee(1, "Jose", "12345678911", "JoseLTDa",LocalTime.of(7,0), LocalTime.of(17,0));
    @Getter
    private static EmployeeModel employeeValidModel = new EmployeeModel(1, "Jose", "12345678911", "JoseLTDa", LocalTime.of(7,0), LocalTime.of(17,0));
    @Getter
    private static CreateEmployeeModel employeeValidCreateModel = new CreateEmployeeModel("Jose", "12345678911", "JoseLTDa",LocalTime.of(7,0), LocalTime.of(17,0));


    public static List<EmployeeModel> getListEmployeeModelValid(){
        List<EmployeeModel> list = new ArrayList<>();
        list.add(EmployeeMapper.toModel(employeeValid));
        return list;
    }
}
