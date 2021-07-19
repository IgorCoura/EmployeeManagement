package br.com.igorcoura.employeemanagement.Mapper;

import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;

import java.util.List;
import java.util.stream.Collectors;

public class MovementRecordMapper {
    public static MovementRecord toEntity(MovementRecordModel model){
        return new MovementRecord(model.getId(), model.getEmployee(), model.getStartTimeWork(), model.getStartLunchTime(), model.getEndLunchTime(), model.getEndTimeWork(), false);
    }

    public static MovementRecord toEntity(CreateMovementRecordModel model){
        return MovementRecord.builder()
                .startLunchTime(model.getStartLunchTime())
                .startTimeWork(model.getStartTimeWork())
                .endLunchTime(model.getEndLunchTime())
                .endTimeWork(model.getEndTimeWork())
                .isOpen(false)
                .build();
    }

    public static MovementRecordModel toModel(MovementRecord entity){
        return new MovementRecordModel(entity.getId(), entity.getEmployee(), entity.getStartTimeWork(), entity.getStartLunchTime(), entity.getEndLunchTime(), entity.getEndTimeWork());
    }

    public static List<MovementRecordModel> toListModel(List<MovementRecord> entity){
        return entity.stream().map(e -> toModel(e)).collect(Collectors.toList());
    }
}
