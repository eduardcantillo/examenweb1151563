package com.web.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entities.Numero;
public interface NumeroDao extends JpaRepository<Numero,Integer>{

}
