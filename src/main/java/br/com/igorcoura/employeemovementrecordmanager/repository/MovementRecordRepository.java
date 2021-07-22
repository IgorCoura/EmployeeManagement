package br.com.igorcoura.employeemovementrecordmanager.repository;

import br.com.igorcoura.employeemovementrecordmanager.domain.entities.Employee;
import br.com.igorcoura.employeemovementrecordmanager.domain.entities.MovementRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRecordRepository extends JpaRepository<MovementRecord, Long> {

}
