package br.com.igorcoura.employeemanagement.controller;


import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.models.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.EmployeeModel;
import br.com.igorcoura.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeModel> register(@RequestBody @Valid CreateEmployeeModel createEmployeeModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.insert(createEmployeeModel));
    }

    @PutMapping
    public ResponseEntity<EmployeeModel> update(@RequestBody @Valid EmployeeModel employeeModel){
        return ResponseEntity.status(HttpStatus.OK).body(employeeModel);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> recoverAll(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.recoverAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> recover(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
