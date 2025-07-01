package com.parcial.org_persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parcial.org_persona.model.Org_Persona;

@Repository
public interface IOrg_PersonaRepository extends JpaRepository<Org_Persona, Long>{

}
