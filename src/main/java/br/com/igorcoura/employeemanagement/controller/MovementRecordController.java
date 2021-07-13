package br.com.igorcoura.employeemanagement.controller;

import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.NewMovementRecordModel;
import br.com.igorcoura.employeemanagement.service.MovementRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movementrecord")
public class MovementRecordController {

    @Autowired
    MovementRecordService movementRecordService;

    @PostMapping
    public MovementRecord register(@RequestBody NewMovementRecordModel newMovementRecordModel){
        return movementRecordService.insert(newMovementRecordModel);
    }

}
