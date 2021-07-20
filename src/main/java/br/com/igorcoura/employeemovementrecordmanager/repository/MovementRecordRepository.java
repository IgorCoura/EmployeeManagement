package br.com.igorcoura.employeemovementrecordmanager.repository;

import br.com.igorcoura.employeemovementrecordmanager.domain.entities.MovementRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRecordRepository extends JpaRepository<MovementRecord, Long> {
}
