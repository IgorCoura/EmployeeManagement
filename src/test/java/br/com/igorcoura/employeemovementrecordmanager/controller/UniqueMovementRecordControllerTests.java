package br.com.igorcoura.employeemovementrecordmanager.controller;

import br.com.igorcoura.employeemovementrecordmanager.domain.models.movementRecord.CreateUniqueMovementRecordModel;
import br.com.igorcoura.employeemovementrecordmanager.services.UniqueMovementRecordService;
import br.com.igorcoura.employeemovementrecordmanager.utils.MovementRecordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UniqueMovementRecordControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniqueMovementRecordService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void uniqueMovementRecordRegisterValid() throws Exception {
        var model = MovementRecordUtils.getMovementRecordModelValidAllParameters();
        var create = new CreateUniqueMovementRecordModel(model.getIdEmployee(), model.getEndTimeWork());
        when(service.insert(any(CreateUniqueMovementRecordModel.class))).thenReturn(model);
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/uniquemovementrecord")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(create))).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        assertEquals(objectMapper.writeValueAsString(model), response.getResponse().getContentAsString());
    }

    @Test
    void uniqueMovementRecordRegisterInvalid() throws Exception {
        var create = new CreateUniqueMovementRecordModel();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/uniquemovementrecord")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(create))).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
