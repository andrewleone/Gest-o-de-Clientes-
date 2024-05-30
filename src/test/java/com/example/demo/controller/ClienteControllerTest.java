package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void testGetClienteByIdInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid clienteId"));
    }

    @Test
    public void testGetClienteByIdNotFound() throws Exception {
        when(clienteService.getClienteById(1L)).thenThrow(new ResourceNotFoundException("Cliente not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cliente not found"));
    }

    @Test
    public void testGetClienteByIdSuccess() throws Exception {
        // Adicione um mock de cliente e configure a resposta esperada do serviço
    }
}
