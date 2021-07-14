package br.com.igorcoura.employeemanagement.services;

import br.com.igorcoura.employeemanagement.domain.entities.Teste;
import br.com.igorcoura.employeemanagement.repository.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesteService {



    final private TesteRepository testeRepository;

    @Autowired
    public TesteService(TesteRepository testeRepository) {
        this.testeRepository = testeRepository;
    }


    public Teste save(Teste teste){
        return testeRepository.save(teste);
    }
}
