package com.restauranteAnalisis.Restaruanteback.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idempleado")
	private int idempleado;
	
	@Column(name = "idtienda")
	private int idtienda;
	
	@Column(name = "pnombre")
	private String pnombre;

	@Column(name = "snombre")
	private String snombre;

	@Column(name = "papelli")
	private String papelli;

	@Column(name = "sapelli")
	private String sapelli;

	public int getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	public int getIdtienda() {
		return idtienda;
	}

	public void setIdtienda(int idtienda) {
		this.idtienda = idtienda;
	}

	public String getPnombre() {
		return pnombre;
	}

	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}

	public String getSnombre() {
		return snombre;
	}

	public void setSnombre(String snombre) {
		this.snombre = snombre;
	}

	public String getPapelli() {
		return papelli;
	}

	public void setPapelli(String papelli) {
		this.papelli = papelli;
	}

	public String getSapelli() {
		return sapelli;
	}

	public void setSapelli(String sapelli) {
		this.sapelli = sapelli;
	}

	
	
}
