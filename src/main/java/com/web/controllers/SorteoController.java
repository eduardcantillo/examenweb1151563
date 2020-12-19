package com.web.controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.web.dao.ISorteoDao;
import com.web.dao.NumeroDao;
import com.web.entities.Boleta;
import com.web.entities.Numero;
import com.web.entities.Sorteo;
import com.web.dao.BoletaDao;

@Controller
public class SorteoController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISorteoDao sorteoDao;
	
	@Autowired
	private BoletaDao boletaDao;
	
	@Autowired
	private NumeroDao numeroDao;

	@GetMapping({"/registrar","/"})
	public String sorteo() {
		return "sorteo";
	}
	
	@PostMapping("/insertar")
	public String registrar(@RequestParam(name="nombre") String nombre, @RequestParam(name="boletas")int boletas,
			@RequestParam(name="numeros") int numeros,@RequestParam(name="maximo") int maximo,Model model) {
		
		if(nombre.isBlank() || boletas==0 || numeros==0 || maximo==0) {
		
			return "sorteo";
		}
		
		
			
		
		Sorteo s = new Sorteo();
		s.setNombre(nombre);
		s.setBoletas(boletas);
		s.setNumeros(numeros);
		s.setMaximo(maximo);
		s.setFecha(new Date());
		
		model.addAttribute("generado", false);
		sorteoDao.save(s);
		
		logger.info("SORTEO REGISTRADO EXITOSAMENTE");
		
		return "redirect:/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Sorteo> sorteos = sorteoDao.findAll();
		model.addAttribute("sorteos", sorteos);
		
		return "listar";
	}
	
	@GetMapping("/sorteo/generarBoletas/{id}")
	public String GenerarBoleta(@PathVariable (name="id") int id) {
		Sorteo sorteo=sorteoDao.findById(id);
		int digitos =sorteo.getNumeros();
		int cantidad=sorteo.getBoletas();
		
		
		int digitosNumeros=String.valueOf(""+(digitos*cantidad)).length();
		
		List<Numero> arr;
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(digitosNumeros); 
		nf.setGroupingUsed(false);
		nf.setMaximumIntegerDigits(digitosNumeros);
		int number=-1;
		for(;number>= cantidad;) {
			number++;
			arr=new ArrayList<Numero>();
			Boleta boleta=new Boleta();
			boletaDao.save(boleta);
			
			for(int j=0;j<digitos || number>=cantidad;j++) {
				
				arr.add(numeroDao.save(new Numero(nf.format(number),boleta)));
				number++;
			}
			
			
			boleta.setNumeros(arr);
			boleta.setSorteoBean(sorteo);
			boleta=null;
			
			}
	
		
		return "redirect:/listar";
	}
}
