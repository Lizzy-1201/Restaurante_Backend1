package com.restauranteAnalisis.Restaruanteback.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproducto")
	private int idproducto;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "paraventa")
	private int paraventa;
	
	@Column(name = "esproducido")
	private int esproducido;
	
	@Column(name = "idmedida")
	private int idmedida;

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getParaventa() {
		return paraventa;
	}

	public void setParaventa(int paraventa) {
		this.paraventa = paraventa;
	}

	public int getEsproducido() {
		return esproducido;
	}

	public void setEsproducido(int esproducido) {
		this.esproducido = esproducido;
	}

	public int getIdmedida() {
		return idmedida;
	}

	public void setIdmedida(int idmedida) {
		this.idmedida = idmedida;
	}
	
	
	
}
