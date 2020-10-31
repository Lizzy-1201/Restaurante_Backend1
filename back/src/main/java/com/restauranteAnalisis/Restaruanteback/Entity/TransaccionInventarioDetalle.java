package com.restauranteAnalisis.Restaruanteback.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaccion_inventario_det")
public class TransaccionInventarioDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtransacciondet")
	private int idtransacciondet;
	
	@Column(name = "idempleado")
	private int idempleado;
	
	@Column(name = "idproducto")
	private int idproducto;
	
	@Column(name = "unidades")
	private double unidades;
	
	@Column(name = "costototal")
	private double costototal;
	
	@Column(name = "fecha")
	private String fecha;

	public int getIdtransacciondet() {
		return idtransacciondet;
	}

	public void setIdtransacciondet(int idtransacciondet) {
		this.idtransacciondet = idtransacciondet;
	}

	public int getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public double getUnidades() {
		return unidades;
	}

	public void setUnidades(double unidades) {
		this.unidades = unidades;
	}

	public double getCostototal() {
		return costototal;
	}

	public void setCostototal(double costototal) {
		this.costototal = costototal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
	
}
