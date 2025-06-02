package com.parcial.org_persona.service;

import java.util.List;

import com.parcial.org_persona.model.Org_Persona;

public interface IOrg_PersonaService 
{
	List<Org_Persona> findAll();
	Org_Persona findById(Long id);
	void save(Org_Persona p);
	void deleteById(Long id);
}
