package br.com.igorcoura.employeemanagement.services.interfaces;

import br.com.igorcoura.employeemanagement.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemanagement.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.ReturnEmployeeModel;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IEmployeeService {

    public EmployeeModel insert(CreateEmployeeModel employeeModel);

    public EmployeeModel update(EmployeeModel employeeModel);

    public List<ReturnEmployeeModel> recoverAll();

    public ReturnEmployeeModel recover(long id);

    public void delete(long id);

}
