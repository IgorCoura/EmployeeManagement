package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.Mapper.EmployeeMapper;
import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.models.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.EmployeeModel;
import br.com.igorcoura.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    public List<EmployeeModel> recoverAll(){
        var entity = employeeRepository.findAll();
        return EmployeeMapper.toModel(entity);
    }

    public EmployeeModel recover(long id) {
        var entity = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with "+ id +" not found"));
        return EmployeeMapper.toModel(entity);
    }

    public void delete(long id){
        employeeRepository.deleteById(id);
    }


}
