package com.parcial.org_persona.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class Org_PersonaTest {

    @Test
    void testBuilder() {
        Org_Persona persona = Org_Persona.builder()
                .id_usuario(1L)
                .nombre("Ana")
                .apellido_p("Martínez")
                .apellido_m("López")
                .correo("ana@example.com")
                .telefono(555123456)
                .direccion("Calle Principal 123")
                .fecha_creacion(LocalDate.now())
                .contraseña("secure123")
                .build();
        
        assertEquals("Ana", persona.getNombre());
        assertEquals("Martínez", persona.getApellido_p());
        assertEquals(555123456, persona.getTelefono());
        assertNotNull(persona.getFecha_creacion());
    }

    @Test
    void testEqualsAndHashCode() {
        Org_Persona persona1 = Org_Persona.builder().id_usuario(1L).build();
        Org_Persona persona2 = Org_Persona.builder().id_usuario(1L).build();
        Org_Persona persona3 = Org_Persona.builder().id_usuario(2L).build();
        
        assertEquals(persona1, persona2);
        assertNotEquals(persona1, persona3);
        assertEquals(persona1.hashCode(), persona2.hashCode());
    }

    @Test
    void testToString() {
        Org_Persona persona = Org_Persona.builder()
                .id_usuario(1L)
                .nombre("Carlos")
                .build();
        
        String toString = persona.toString();
        assertTrue(toString.contains("Org_Persona"));
        assertTrue(toString.contains("id_usuario=1"));
        assertTrue(toString.contains("nombre=Carlos"));
    }
}