package br.com.igorcoura.employeemovementrecordmanager.repository;

import br.com.igorcoura.employeemovementrecordmanager.domain.entities.Employee;
import br.com.igorcoura.employeemovementrecordmanager.domain.entities.MovementRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MovementRecordCustomRepository {

    @Autowired
    private EntityManager entityManager;

    public List<MovementRecord> findAll(Long id, Employee employee, Boolean isOpen){
        String query = "select M from MovementRecord as M";
        String condition = " where ";

        if(id != null){
            query += condition + "M.id = :id";
            condition = " and ";
        }

        if(employee != null){
            query += condition + "M.employee = :idEmployee";
            condition = " and ";
        }

        if(isOpen != null){
            query += condition + "M.isOpen = :isOpen";
            condition = " and ";
        }

        var q = entityManager.createQuery(query, MovementRecord.class);

        if(id != null){
            q.setParameter("id", id);
        }

        if(employee != null){
            q.setParameter("idEmployee", employee);
        }

        if(isOpen != null){
            q.setParameter("isOpen", isOpen);
        }

        return q.getResultList();
    }


}
