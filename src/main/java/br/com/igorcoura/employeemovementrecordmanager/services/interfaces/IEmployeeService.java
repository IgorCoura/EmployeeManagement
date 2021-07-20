package br.com.igorcoura.employeemovementrecordmanager.services.interfaces;

import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.ReturnEmployeeModel;

import java.util.List;

public interface IEmployeeService {

    public EmployeeModel insert(CreateEmployeeModel employeeModel);

    public EmployeeModel update(EmployeeModel employeeModel);

    public List<ReturnEmployeeModel> recoverAll();

    public ReturnEmployeeModel recover(long id);

    public void delete(long id);

}
