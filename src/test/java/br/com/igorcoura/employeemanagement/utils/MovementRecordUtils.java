package br.com.igorcoura.employeemanagement.utils;

import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.NewMovementRecordModel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class MovementRecordUtils {

    @Getter
    private static NewMovementRecordModel newMovementRecordValidTimeToStartWork = new NewMovementRecordModel(51, LocalDateTime.of(2021,7,14,7,10));
    @Getter
    private static NewMovementRecordModel newMovementRecordValidTimeToStartLunch = new NewMovementRecordModel(51, LocalDateTime.of(2021,7,14,12,10));
    @Getter
    private static NewMovementRecordModel newMovementRecordValidTimeToEndLunch = new NewMovementRecordModel(51, LocalDateTime.of(2021,7,14,13,10));
    @Getter
    private static NewMovementRecordModel newMovementRecordValidTimeToEndWork = new NewMovementRecordModel(51, LocalDateTime.of(2021,7,14,15,10));
    @Getter
    private static NewMovementRecordModel newMovementRecordInvalidTime = new NewMovementRecordModel(51, LocalDateTime.of(2021,7,15,15,10));
    @Getter
    private static Employee employee = new Employee(1, "Jose", "123456789", "JoseLTDa", LocalTime.of(7,0), LocalTime.of(17,0), 1);
    @Getter
    private static MovementRecord movementRecordTrueWithoutEmployee = MovementRecord.builder()
            .id(1)
            .isOpen(true)
            .build();
    @Getter
    private static MovementRecord movementRecordTrueWithoutTime = MovementRecord.builder()
            .id(2)
            .employee(employee)
            .isOpen(true)
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyStartTimeWork = MovementRecord.builder()
            .id(3)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,14,7,10))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyStartLunchTime = MovementRecord.builder()
            .id(4)
            .employee(employee)
            .isOpen(true)
            .startLunchTime(LocalDateTime.of(2021,7,14,12,5))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyEndLunchTime = MovementRecord.builder()
            .id(5)
            .employee(employee)
            .isOpen(true)
            .endLunchTime(LocalDateTime.of(2021,7,14,13,5))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyEndTimeWork = MovementRecord.builder()
            .id(6)
            .employee(employee)
            .isOpen(true)
            .endTimeWork(LocalDateTime.of(2021,7,14,15,15))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueWithStartTimeWorkAndStartLunchTime = MovementRecord.builder()
            .id(7)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,14,7,15))
            .startLunchTime(LocalDateTime.of(2021,7,14,12,5))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueWithStartTimeWorkAndEndLunchTime = MovementRecord.builder()
            .id(8)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,14,7,15))
            .endLunchTime(LocalDateTime.of(2021,7,14,1,5))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueWithStartTimeWorkAndEndTimeWork = MovementRecord.builder()
            .id(9)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,14,7,15))
            .endTimeWork(LocalDateTime.of(2021,7,14,17,5))
            .build();

    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyInvalidStartTimeWorkBeforeWithOnlyStartLunchTime = MovementRecord.builder()
            .id(10)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,13,7,10))
            .startLunchTime(LocalDateTime.of(2021,7,13,12,10))
            .build();

    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyInvalidStartTimeWorkBeforeWithOnlyEndLunchTime = MovementRecord.builder()
            .id(11)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,13,7,10))
            .endLunchTime(LocalDateTime.of(2021,7,13,12,10))
            .build();
    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyInvalidStartTimeWorkBeforeWithStartEndEndLunchTime = MovementRecord.builder()
            .id(12)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,13,7,10))
            .startLunchTime(LocalDateTime.of(2021,7,13,12,10))
            .endLunchTime(LocalDateTime.of(2021,7,13,13,10))
            .build();

    @Getter
    private static MovementRecord movementRecordRecordTrueOnlyInvalidStartTimeWorkAfter = MovementRecord.builder()
            .id(13)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(LocalDateTime.of(2021,7,15,7,10))
            .build();

    public static List<MovementRecord> createListMovementRecord(){
        List<MovementRecord> list = new ArrayList<MovementRecord>();
        list.add(movementRecordTrueWithoutTime); // it will be closed
        list.add(movementRecordRecordTrueOnlyStartTimeWork ); // it will be closed
        list.add(movementRecordRecordTrueOnlyStartLunchTime); // it will be closed
        list.add(movementRecordRecordTrueOnlyEndLunchTime); // it will be closed
        list.add(movementRecordRecordTrueOnlyEndTimeWork); // it will be closed
        list.add(movementRecordRecordTrueWithStartTimeWorkAndStartLunchTime); // it will be used
        list.add(movementRecordRecordTrueWithStartTimeWorkAndEndLunchTime); // it will be closed
        list.add(movementRecordRecordTrueWithStartTimeWorkAndEndTimeWork); // it will be closed
        list.add(movementRecordRecordTrueOnlyInvalidStartTimeWorkBeforeWithOnlyStartLunchTime); // it will be closed
        list.add(movementRecordRecordTrueOnlyInvalidStartTimeWorkAfter); // it will be closed
        list.add(movementRecordRecordTrueOnlyInvalidStartTimeWorkBeforeWithOnlyEndLunchTime); // it will be closed
        list.add(movementRecordRecordTrueOnlyInvalidStartTimeWorkBeforeWithStartEndEndLunchTime); // it will be closed
        return list;
    }





}
