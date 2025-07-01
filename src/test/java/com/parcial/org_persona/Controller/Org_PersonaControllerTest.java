package com.parcial.org_persona.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcial.org_persona.controller.Org_PersonaController;
import com.parcial.org_persona.model.Org_Persona;
import com.parcial.org_persona.service.IOrg_PersonaService;

@WebMvcTest(Org_PersonaController.class)
public class Org_PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IOrg_PersonaService orgPersonaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Org_Persona persona;

    @BeforeEach
    void setUp() {
        persona = Org_Persona.builder()
                .id_usuario(1L)
                .nombre("Juan")
                .apellido_p("Pérez")
                .apellido_m("Gómez")
                .correo("juan@example.com")
                .telefono(123456789)
                .direccion("Calle 123")
                .fecha_creacion(LocalDate.now())
                .contraseña("password123")
                .build();
    }

    @Test
    void testGuardarUsuario() throws Exception {
        doNothing().when(orgPersonaService).save(any(Org_Persona.class));
        
        mockMvc.perform(post("/api/phoenix_dinamic/usuario/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(persona)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.correo").value("juan@example.com"));
        
        verify(orgPersonaService, times(1)).save(any(Org_Persona.class));
    }

    @Test
    void testObtenerTodos() throws Exception {
        List<Org_Persona> personas = Arrays.asList(persona);
        when(orgPersonaService.findAll()).thenReturn(personas);
        
        mockMvc.perform(get("/api/phoenix_dinamic/usuario/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido_p").value("Pérez"));
        
        verify(orgPersonaService, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() throws Exception {
        when(orgPersonaService.findById(1L)).thenReturn(persona);
        
        mockMvc.perform(get("/api/phoenix_dinamic/usuario/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_usuario").value(1))
                .andExpect(jsonPath("$.telefono").value(123456789));
        
        verify(orgPersonaService, times(1)).findById(1L);
    }

    @Test
    void testEliminarPorId() throws Exception {
        when(orgPersonaService.findById(1L)).thenReturn(persona);
        doNothing().when(orgPersonaService).deleteById(1L);
        
        mockMvc.perform(delete("/api/phoenix_dinamic/usuario/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_usuario").value(1));
        
        verify(orgPersonaService, times(1)).deleteById(1L);
    }

    @Test
    void testActualizarDatos() throws Exception {
        Org_Persona personaActualizada = Org_Persona.builder()
                .nombre("Juan Carlos")
                .apellido_p("Pérez")
                .apellido_m("González")
                .correo("juan.carlos@example.com")
                .telefono(987654321)
                .direccion("Avenida 456")
                .contraseña("newpassword123")
                .build();

        when(orgPersonaService.findById(1L)).thenReturn(persona);
        doNothing().when(orgPersonaService).save(any(Org_Persona.class));
        
        mockMvc.perform(put("/api/phoenix_dinamic/usuario/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personaActualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Carlos"))
                .andExpect(jsonPath("$.telefono").value(987654321));
        
        verify(orgPersonaService, times(1)).findById(1L);
        verify(orgPersonaService, times(1)).save(any(Org_Persona.class));
    }
}