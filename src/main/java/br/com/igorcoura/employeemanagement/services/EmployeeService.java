package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemanagement.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.ReturnEmployeeModel;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class EmployeeService {

    final private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


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
