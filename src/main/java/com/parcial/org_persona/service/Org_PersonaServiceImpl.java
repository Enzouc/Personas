package com.parcial.org_persona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.org_persona.model.Org_Persona;
import com.parcial.org_persona.repository.IOrg_PersonaRepository;

@Service
public class Org_PersonaServiceImpl implements IOrg_PersonaService{

	@Autowired
	private IOrg_PersonaRepository repository;
	
	@Override
	public List<Org_Persona> findAll() {
		return repository.findAll();
	}

	@Override
	public Org_Persona findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public void save(Org_Persona p) {
		repository.save(p);
		
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

}
