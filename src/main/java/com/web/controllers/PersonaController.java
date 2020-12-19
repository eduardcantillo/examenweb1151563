package com.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dao.IPersonaDao;
import com.web.dao.ISorteoDao;
import com.web.entities.Persona;
import com.web.entities.Sorteo;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private IPersonaDao personaDao;

	@Autowired
	private ISorteoDao sorteoDao;
	@RequestMapping(value= "/registrar/{id}")
	public String persona(@PathVariable(value = "id") int id,Model model) {
		Persona persona = null;
		if(id > 0) {
			model.addAttribute("sorteo",sorteoDao.findById(id));
			return "registrar";
		}
		
		return "redirect:/listar";
	}
	
	@PostMapping("/registrar/")
	public String registrar(@RequestParam(name="idsorteo") int id,
			@RequestParam(name="nombre") String nombre,@RequestParam(name="documento")String documento,
			@RequestParam(name="email")String email){
		
		Persona persona = new Persona();
	
		if(id > 0) {			
			persona.setNombre(nombre);
			persona.setDocumento(documento);
			persona.setEmail(email);
			persona.setSorteoBean(sorteoDao.findById(id));
			personaDao.save(persona);
		}
		
		
		return "redirect:/listar";
	}
}
