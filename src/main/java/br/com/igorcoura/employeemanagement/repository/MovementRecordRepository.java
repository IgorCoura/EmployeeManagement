package br.com.igorcoura.employeemanagement.repository;

import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRecordRepository extends JpaRepository<MovementRecord, Long> {
}
