package br.com.igorcoura.employeemanagement.repository;

import br.com.igorcoura.employeemanagement.domain.entities.Teste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {
}
