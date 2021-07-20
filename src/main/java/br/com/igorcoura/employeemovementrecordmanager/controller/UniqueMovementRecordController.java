package br.com.igorcoura.employeemovementrecordmanager.controller;

import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.CreateUniqueMovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.services.interfaces.IUniqueMovementRecordService;
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
    private IUniqueMovementRecordService service;

    @PostMapping
    public ResponseEntity<MovementRecordModel> register(@RequestBody @Valid CreateUniqueMovementRecordModel model){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(model));
    }

}
