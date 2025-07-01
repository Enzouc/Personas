package com.parcial.org_persona.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "org_persona")
public class Org_Persona 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	private String nombre;
	private String apellido_p;
	private String apellido_m;
	@Column(unique = true)
	private String correo;
	private int telefono;
	private String direccion;
	
	private LocalDate fecha_creacion;
	private String contraseña;
	
}
