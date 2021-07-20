package br.com.igorcoura.employeemovementrecordmanager.repository;

import br.com.igorcoura.employeemovementrecordmanager.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
