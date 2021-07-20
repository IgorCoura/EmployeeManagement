package br.com.igorcoura.employeemanagement.controller;

import br.com.igorcoura.employeemanagement.domain.models.employee.CreateEmployeeModel;
import br.com.igorcoura.employeemanagement.domain.models.employee.EmployeeModel;
import br.com.igorcoura.employeemanagement.services.EmployeeService;
import br.com.igorcoura.employeemanagement.services.interfaces.IEmployeeService;
import br.com.igorcoura.employeemanagement.utils.EmployeeUtils;
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

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void employeeGetAllValid() throws Exception {
        var listEmployee = EmployeeUtils.getListEmployeeReturnModelValid();
        when(employeeService.recoverAll()).thenReturn(listEmployee);
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(listEmployee), response.getResponse().getContentAsString());
    }

    @Test
    void employeeGetByIDValid() throws Exception {
        var employee = EmployeeUtils.getEmployeeValidReturnModel();
        when(employeeService.recover(any(Long.class))).thenReturn(employee);
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{id}", "/1")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals(objectMapper.writeValueAsString(employee), response.getResponse().getContentAsString());
    }

    @Test
    void employeeGetByIDNotFound() throws Exception {
        when(employeeService.recover(any(Long.class))).thenThrow(new EntityNotFoundException("Employee with id = 1, not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{id}", "/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void employeeGetByIDBadRequest() throws Exception {
        var employee = EmployeeUtils.getEmployeeValidReturnModel();
        when(employeeService.recover(any(Long.class))).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{id}", "/d")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void employeeRegisterValid() throws Exception {
        var createEmployee = EmployeeUtils.getEmployeeValidCreateModel();
        var employee = EmployeeUtils.getEmployeeValidModel();
        when(employeeService.insert(any(CreateEmployeeModel.class))).thenReturn(employee);
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createEmployee))).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        assertEquals(objectMapper.writeValueAsString(employee), response.getResponse().getContentAsString());
    }

    @Test
    void employeeRegisterInvalid() throws Exception {
        var createEmployee = new CreateEmployeeModel();
        var employee = new EmployeeModel();
        var expectedContent = "\"objectName\":\"createEmployeeModel\"";
        when(employeeService.insert(any(CreateEmployeeModel.class))).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createEmployee))).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void employeeUpdateValid() throws Exception {
        var employeeModel = EmployeeUtils.getEmployeeValidModel();
        var employeeString = objectMapper.writeValueAsString(employeeModel);

        when(employeeService.update(any(EmployeeModel.class))).thenReturn(employeeModel);

        var response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employee")
                .contentType("application/json")
                .content(employeeString))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertEquals(employeeString, response.getResponse().getContentAsString());
    }

    @Test
    void employeeUpdateInvalid() throws Exception {
        var employeeModel = new EmployeeModel();
        var employeeString = objectMapper.writeValueAsString(employeeModel);

        when(employeeService.update(any(EmployeeModel.class))).thenReturn(employeeModel);

        var response = mockMvc.perform(MockMvcRequestBuilders.put("/api/employee")
                .contentType("application/json")
                .content(employeeString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void employeeDeleteValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}", "/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void employeeDeleteNotFound() throws Exception {
        doThrow(new EmptyResultDataAccessException(5)).when(employeeService).delete(5);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}", "/5")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    void employeeDeleteBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}", "/d")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
