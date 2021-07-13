package br.com.igorcoura.employeemanagement.repository;

import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
