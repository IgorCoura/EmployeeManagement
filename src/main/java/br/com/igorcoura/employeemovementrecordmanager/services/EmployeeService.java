package br.com.igorcoura.employeemovementrecordmanager.services;

import br.com.igorcoura.employeemovementrecordmanager.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.employee.ReturnEmployeeModel;
import br.com.igorcoura.employeemovementrecordmanager.repository.EmployeeRepository;
import br.com.igorcoura.employeemovementrecordmanager.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeModel insert(CreateEmployeeModel employeeModel){
        var entity = employeeRepository.save(EmployeeMapper.toEntity(employeeModel));
        return EmployeeMapper.toModel(entity);
    }

    public EmployeeModel update(EmployeeModel employeeModel){
        var entity = employeeRepository.save(EmployeeMapper.toEntity(employeeModel));
        return EmployeeMapper.toModel(entity);
    }

    public List<ReturnEmployeeModel> recoverAll(){
        var entity = employeeRepository.findAll();
        return EmployeeMapper.toReturnModel(entity);
    }

    public ReturnEmployeeModel recover(long id){
        var employee = employeeRepository.findById(id).map(e -> EmployeeMapper.toReturnModel(e));
        return employee.orElseThrow(() -> new EntityNotFoundException("Employee with id = "+id+", Not Found"));
    }

    public void delete(long id){
        employeeRepository.deleteById(id);
    }


}
