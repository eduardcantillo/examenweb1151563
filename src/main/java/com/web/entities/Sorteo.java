package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sorteo database table.
 * 
 */
@Entity
@NamedQuery(name="Sorteo.findAll", query="SELECT s FROM Sorteo s")
public class Sorteo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int boletas;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private int maximo;

	private String nombre;

	private int numeros;
	
	
	

	public Sorteo(int id, int boletas, Date fecha, int maximo, String nombre, int numeros) {
		super();
		this.id = id;
		this.boletas = boletas;
		this.fecha = fecha;
		this.maximo = maximo;
		this.nombre = nombre;
		this.numeros = numeros;
	}

	//bi-directional many-to-one association to Boleta
	@OneToMany(mappedBy="sorteoBean")
	private List<Boleta> boletasSet;

	//bi-directional many-to-one association to Persona
	@OneToMany(mappedBy="sorteoBean")
	private List<Persona> personas;

	public Sorteo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoletas() {
		return this.boletas;
	}

	public void setBoletas(int boletas) {
		this.boletas = boletas;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getMaximo() {
		return this.maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeros() {
		return this.numeros;
	}

	public void setNumeros(int numeros) {
		this.numeros = numeros;
	}

	public List<Boleta> getBoletasSet() {
		return this.boletasSet;
	}

	public void setBoletasSet(List<Boleta> boletasSet) {
		this.boletasSet = boletasSet;
	}

	public Boleta addBoletasSet(Boleta boletasSet) {
		getBoletasSet().add(boletasSet);
		boletasSet.setSorteoBean(this);

		return boletasSet;
	}

	public Boleta removeBoletasSet(Boleta boletasSet) {
		getBoletasSet().remove(boletasSet);
		boletasSet.setSorteoBean(null);

		return boletasSet;
	}

	public List<Persona> getPersonas() {
		return this.personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona addPersona(Persona persona) {
		getPersonas().add(persona);
		persona.setSorteoBean(this);

		return persona;
	}

	public Persona removePersona(Persona persona) {
		getPersonas().remove(persona);
		persona.setSorteoBean(null);

		return persona;
	}

}