package com.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entities.Sorteo;

public interface ISorteoDao extends JpaRepository<Sorteo, Integer> {
	
	public Sorteo findById(int id);
}
