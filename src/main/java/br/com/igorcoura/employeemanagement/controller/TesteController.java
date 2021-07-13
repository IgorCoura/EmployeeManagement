package br.com.igorcoura.employeemanagement.controller;

import br.com.igorcoura.employeemanagement.domain.entities.Teste;
import br.com.igorcoura.employeemanagement.service.TesteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teste")
@Api(value="Api test")
@CrossOrigin(origins = "*")
public class TesteController {

    @Autowired
    TesteService testeService;

    @PostMapping
    @ApiOperation(value = "Create register test")
    public Teste createTeste(@RequestBody Teste teste){
        return testeService.save(teste);
    }
}
