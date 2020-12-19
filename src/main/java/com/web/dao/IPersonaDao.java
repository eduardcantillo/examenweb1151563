package com.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entities.Persona;

public interface IPersonaDao extends JpaRepository<Persona, Integer>{
	
	public Persona findById (int id);
}
