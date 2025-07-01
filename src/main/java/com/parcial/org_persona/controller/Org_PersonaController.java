package com.parcial.org_persona.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parcial.org_persona.model.Org_Persona;
import com.parcial.org_persona.service.IOrg_PersonaService;

@RestController
@RequestMapping("/api/phoenix_dinamic/usuario")
public class Org_PersonaController 
{
	@Autowired
	private IOrg_PersonaService service;
	
	@PostMapping("/create")
	public Org_Persona guardarUsuario(@RequestBody Org_Persona org_Persona) 
	{
		org_Persona.setFecha_creacion(LocalDate.now());
		service.save(org_Persona);
		return org_Persona;
	}
	
	@GetMapping("/getAll")
	public List<Org_Persona> obtenerTodos(){
		return service.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Org_Persona obtenerPorId(@PathVariable(name = "id") Long id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Org_Persona eliminarPorId(@PathVariable(name = "id") Long id) {
		Org_Persona p=service.findById(id);
		service.deleteById(id);
		return p;
	}
	
	@PutMapping("/actualizar/{id}")
	public Org_Persona actualizarDatos(@PathVariable("id")Long id, @RequestBody Org_Persona org) {
		Org_Persona usuarioExistente= service.findById(id);
		service.deleteById(id);
		usuarioExistente.setId_usuario(org.getId_usuario());
		usuarioExistente.setNombre(org.getNombre());
		usuarioExistente.setApellido_p(org.getApellido_p());
		usuarioExistente.setApellido_m(org.getApellido_m());
		usuarioExistente.setDireccion(org.getDireccion());
		usuarioExistente.setCorreo(org.getCorreo());
		usuarioExistente.setTelefono(org.getTelefono());
		usuarioExistente.setContraseña(org.getContraseña());
		service.save(usuarioExistente);
		return usuarioExistente;
	}
	
	

}
