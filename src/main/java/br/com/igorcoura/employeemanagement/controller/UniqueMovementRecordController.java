package br.com.igorcoura.employeemanagement.controller;

import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.services.interfaces.IMovementRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/uniquemovementrecord")
public class UniqueMovementRecordController {

    @Autowired
    private IMovementRecordService movementRecordService;

    @PostMapping
    public ResponseEntity<MovementRecordModel> register(@RequestBody @Valid CreateMovementRecordModel model){
        return ResponseEntity.status(HttpStatus.CREATED).body(movementRecordService.insert(model));
    }

}
