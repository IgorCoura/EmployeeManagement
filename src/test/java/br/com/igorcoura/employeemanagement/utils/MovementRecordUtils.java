package br.com.igorcoura.employeemanagement.utils;

import br.com.igorcoura.employeemanagement.domain.entities.Employee;
import br.com.igorcoura.employeemanagement.domain.entities.MovementRecord;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.NewUniqueMovementRecordModel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class MovementRecordUtils {

    private static LocalDateTime startTimeWork = LocalDateTime.of(2021,7,14,7,10);
    private static LocalDateTime startLunchTime = LocalDateTime.of(2021,7,14,12,10);
    private static LocalDateTime endLunchTime = LocalDateTime.of(2021,7,14,13,10);
    private static LocalDateTime endTimeWork = LocalDateTime.of(2021,7,14,15,10);

    @Getter
    private static Employee employee = EmployeeUtils.getEmployeeValid();
    @Getter
    private static NewUniqueMovementRecordModel newMovementRecordValidTimeToStartWork = new NewUniqueMovementRecordModel(employee.getId(), startTimeWork);
    @Getter
    private static NewUniqueMovementRecordModel newMovementRecordValidTimeToStartLunch = new NewUniqueMovementRecordModel(employee.getId(), startLunchTime);
    @Getter
    private static NewUniqueMovementRecordModel newMovementRecordValidTimeToEndLunch = new NewUniqueMovementRecordModel(employee.getId(), endLunchTime);
    @Getter
    private static NewUniqueMovementRecordModel newMovementRecordValidTimeToEndWork = new NewUniqueMovementRecordModel(employee.getId(), endTimeWork);
    @Getter
    private static NewUniqueMovementRecordModel newMovementRecordInvalidTime = new NewUniqueMovementRecordModel(employee.getId(), startLunchTime.plusDays(1));
    @Getter
    private static MovementRecord movementRecordCloseValidAllParameters = new MovementRecord(1, employee, startTimeWork, startLunchTime, endLunchTime, endTimeWork, false);
    @Getter
    private static CreateMovementRecordModel createMovementRecordModelValidAllParameters = new CreateMovementRecordModel(employee.getId(), startTimeWork, startLunchTime, endLunchTime, endTimeWork);

    @Getter
    private static MovementRecord movementRecordOpenWithoutEmployee = MovementRecord.builder()
            .id(1)
            .isOpen(true)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenWithoutTime = MovementRecord.builder()
            .id(2)
            .employee(employee)
            .isOpen(true)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenOnlyStartTimeWork = MovementRecord.builder()
            .id(3)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenOnlyStartLunchTime = MovementRecord.builder()
            .id(4)
            .employee(employee)
            .isOpen(true)
            .startLunchTime(startLunchTime)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenOnlyEndLunchTime = MovementRecord.builder()
            .id(5)
            .employee(employee)
            .isOpen(true)
            .endLunchTime(endLunchTime)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenOnlyEndTimeWork = MovementRecord.builder()
            .id(6)
            .employee(employee)
            .isOpen(true)
            .endTimeWork(endTimeWork)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenWithStartTimeWorkAndStartLunchTime = MovementRecord.builder()
            .id(7)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork)
            .startLunchTime(startLunchTime)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenWithStartTimeWorkAndEndLunchTime = MovementRecord.builder()
            .id(8)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork)
            .endLunchTime(endLunchTime)
            .build();
    @Getter
    private static MovementRecord movementRecordOpenWithStartTimeWorkAndEndTimeWork = MovementRecord.builder()
            .id(9)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork)
            .endTimeWork(endTimeWork)
            .build();

    @Getter
    private static MovementRecord movementRecordOpenBeforeTimeInvalidWithStartTimeWorkAndStartLunchTime = MovementRecord.builder()
            .id(10)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork.plusDays(-1))
            .startLunchTime(startLunchTime.plusDays(-1))
            .build();

    @Getter
    private static MovementRecord movementRecordOpenBeforeTimeInvalidWithStartTimeWorkAndEndLunchTime = MovementRecord.builder()
            .id(11)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork.plusDays(-1))
            .endLunchTime(endLunchTime.plusDays(-1))
            .build();
    @Getter
    private static MovementRecord movementRecordOpenBeforeTimeInvalidWithStartTimeWorkAndStartAndEndLunchTime = MovementRecord.builder()
            .id(12)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork.plusDays(-1))
            .startLunchTime(startLunchTime.plusDays(-1))
            .endLunchTime(endLunchTime.plusDays(-1))
            .build();

    @Getter
    private static MovementRecord movementRecordOpenAfterTimeInvalidOnlyStartTimeWork = MovementRecord.builder()
            .id(13)
            .employee(employee)
            .isOpen(true)
            .startTimeWork(startTimeWork.plusDays(1))
            .build();


    @Getter
    private static MovementRecordModel movementRecordModelValidAllParameters = MovementRecordModel.builder()
            .id(14)
            .employee(employee)
            .startTimeWork(startTimeWork)
            .startLunchTime(startLunchTime)
            .endLunchTime(endLunchTime)
            .endTimeWork(endTimeWork)
            .build();

    @Getter
    private static MovementRecord movementRecordOnlyEmployee = MovementRecord.builder()
            .id(15)
            .employee(employee)
            .isOpen(false)
            .build();

    public static List<MovementRecord> createListMovementRecord(){
        List<MovementRecord> list = new ArrayList<MovementRecord>();
        list.add(movementRecordOpenWithoutEmployee);
        list.add(movementRecordOpenOnlyEndLunchTime); // it will be closed
        list.add(movementRecordOpenWithoutTime); // it will be closed
        list.add(movementRecordOpenOnlyStartTimeWork); // it will be closed
        list.add(movementRecordOpenOnlyStartLunchTime); // it will be closed
        list.add(movementRecordOpenOnlyEndTimeWork); // it will be closed
        list.add(movementRecordOpenWithStartTimeWorkAndStartLunchTime); // it will be used
        list.add(movementRecordOpenWithStartTimeWorkAndEndLunchTime); // it will be closed
        list.add(movementRecordOpenWithStartTimeWorkAndEndTimeWork); // it will be closed
        list.add(movementRecordOpenBeforeTimeInvalidWithStartTimeWorkAndStartAndEndLunchTime); // it will be closed
        list.add(movementRecordOpenAfterTimeInvalidOnlyStartTimeWork); // it will be closed
        list.add(movementRecordOpenBeforeTimeInvalidWithStartTimeWorkAndEndLunchTime); // it will be closed
        list.add(movementRecordOpenBeforeTimeInvalidWithStartTimeWorkAndStartAndEndLunchTime); // it will be closed
        return list;
    }





}
