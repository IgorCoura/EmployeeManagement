package br.com.igorcoura.employeemanagement.controller;

import br.com.igorcoura.employeemanagement.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.CreateMovementRecordModel;
import br.com.igorcoura.employeemanagement.domain.models.movementRecord.MovementRecordModel;
import br.com.igorcoura.employeemanagement.services.MovementRecordService;
import br.com.igorcoura.employeemanagement.utils.EmployeeUtils;
import br.com.igorcoura.employeemanagement.utils.MovementRecordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class MovementRecordControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementRecordService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void movementRecordRegisterValid() throws Exception {
        var create = MovementRecordUtils.getCreateMovementRecordModelValidAllParameters();
        var model = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        when(service.insert(any(CreateMovementRecordModel.class))).thenReturn(model);
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/movementrecord")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(create))).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        assertEquals(objectMapper.writeValueAsString(model), response.getResponse().getContentAsString());
    }

    @Test
    void movementRecordRegisterInvalid() throws Exception {
        var create = new CreateMovementRecordModel();
        var model = new MovementRecordModel();
        when(service.insert(any(CreateMovementRecordModel.class))).thenReturn(model);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movementrecord")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(create))).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void movementRecordGetAllValid() throws Exception {
        var list = MovementRecordUtils.getListMovementRecordModel();
        when(service.recoverAll()).thenReturn(list);
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/movementrecord")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(list), response.getResponse().getContentAsString());
    }

    @Test
    void movementRecordGetByIDValid() throws Exception {
        var movement = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        when(service.recover(any(Long.class))).thenReturn(movement);
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/movementrecord/{id}", "/1")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(movement), response.getResponse().getContentAsString());
    }

    @Test
    void movementRecordGetByIDNotFound() throws Exception {
        when(service.recover(any(Long.class))).thenThrow(new EntityNotFoundException("Movement record with id = 1, not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movementrecord/{id}", "/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void movementRecordGetByIDBadRequest() throws Exception {
        var movement = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        when(service.recover(any(Long.class))).thenReturn(movement);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movementrecord/{id}", "/d")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



    @Test
    void movementRecordUpdateValid() throws Exception {
        var model = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        var expectedResponse = objectMapper.writeValueAsString(model);

        when(service.update(any(MovementRecordModel.class), any(Boolean.class))).thenReturn(model);

        var response = mockMvc.perform(MockMvcRequestBuilders.put("/api/movementrecord")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals(expectedResponse, response.getResponse().getContentAsString());
    }

    @Test
    void movementRecordUpdateInvalid() throws Exception {
        var model = new MovementRecordModel();
        var expectedResponse = objectMapper.writeValueAsString(model);

        when(service.update(any(MovementRecordModel.class), any(Boolean.class))).thenReturn(model);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/movementrecord")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void movementRecordDeleteValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movementrecord/{id}", "/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void movementRecordDeleteNotFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(5)).when(service).delete(5);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movementrecord/{id}", "/5")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void movementRecordDeleteBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movementrecord/{id}", "/d")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
