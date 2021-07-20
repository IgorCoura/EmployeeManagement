package br.com.igorcoura.employeemanagement.controller;


import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.ReturnEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.services.interfaces.IMovementRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movementrecord")
public class MovementRecordController {

    @Autowired
    private IMovementRecordService movementRecordService;

    @PostMapping
    public ResponseEntity<MovementRecordModel> register(@RequestBody @Valid CreateMovementRecordModel model){
        return ResponseEntity.status(HttpStatus.CREATED).body(movementRecordService.insert(model));
    }

    @PutMapping
    public ResponseEntity<MovementRecordModel> update(@RequestBody @Valid MovementRecordModel model, @RequestParam(defaultValue = "false", required = false) boolean forceUpdate){
        return ResponseEntity.status(HttpStatus.OK).body(movementRecordService.update(model, forceUpdate));
    }

    @GetMapping
    public ResponseEntity<List<MovementRecordModel>> recoverAll(){
        return ResponseEntity.status(HttpStatus.OK).body(movementRecordService.recoverAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovementRecordModel> recover(@PathVariable("id") long id){
        return  ResponseEntity.status(HttpStatus.OK).body(movementRecordService.recover(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") long id){
        movementRecordService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
