package com.parcial.org_persona.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parcial.org_persona.model.Org_Persona;
import com.parcial.org_persona.repository.IOrg_PersonaRepository;
import com.parcial.org_persona.service.Org_PersonaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class Org_PersonaServiceImplTest {

    @Mock
    private IOrg_PersonaRepository repository;

    @InjectMocks
    private Org_PersonaServiceImpl orgPersonaService;

    private Org_Persona persona1;
    private Org_Persona persona2;

    @BeforeEach
    void setUp() {
        persona1 = Org_Persona.builder()
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

        persona2 = Org_Persona.builder()
                .id_usuario(2L)
                .nombre("María")
                .apellido_p("López")
                .apellido_m("Martínez")
                .correo("maria@example.com")
                .telefono(987654321)
                .direccion("Avenida 456")
                .fecha_creacion(LocalDate.now())
                .contraseña("password456")
                .build();
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(persona1, persona2));
        
        List<Org_Persona> result = orgPersonaService.findAll();
        
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Existente() {
        when(repository.findById(1L)).thenReturn(Optional.of(persona1));
        
        Org_Persona result = orgPersonaService.findById(1L);
        
        assertEquals("Juan", result.getNombre());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NoExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> orgPersonaService.findById(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void testSave() {
    Org_Persona persona = new Org_Persona();    
        when(repository.save(any(Org_Persona.class))).thenReturn(persona);
        orgPersonaService.save(persona);
        verify(repository, times(1)).save(persona);
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(1L);
        
        orgPersonaService.deleteById(1L);
        
        verify(repository, times(1)).deleteById(1L);
    }

}