package br.com.igorcoura.employeemanagement.controller;


import br.com.igorcoura.employeemanagement.domain.models.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.EmployeeModel;
import br.com.igorcoura.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public EmployeeModel register(@RequestBody CreateEmployeeModel createEmployeeModel){
        return employeeService.insert(createEmployeeModel);
    }

    @PutMapping
    public EmployeeModel update(@RequestBody EmployeeModel employeeModel){
        return employeeService.update(employeeModel);
    }

    @GetMapping
    public List<EmployeeModel> recoverAll(){
        return employeeService.recoverAll();
    }

    @GetMapping("/{id}")
    public EmployeeModel recover(@PathVariable("id") long id){
        return employeeService.recover(id);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") long id){
        employeeService.delete(id);
    }
}
