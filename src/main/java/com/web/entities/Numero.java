package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the numero database table.
 * 
 */
@Entity
@NamedQuery(name="Numero.findAll", query="SELECT n FROM Numero n")
public class Numero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String numero;

	//bi-directional many-to-one association to Boleta
	@ManyToOne
	@JoinColumn(name="boleta")
	private Boleta boletaBean;

	public Numero() {
	}
	
	public Numero(String numero,Boleta boleta) {
		this.boletaBean=boleta;
		this.numero=numero;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boleta getBoletaBean() {
		return this.boletaBean;
	}

	public void setBoletaBean(Boleta boletaBean) {
		this.boletaBean = boletaBean;
	}

}