package com.restauranteAnalisis.Restaruanteback.Entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="receta_detalle")
public class RecetaDetalle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idrecetadetalle")
	private int idrecetadetalle;
	
	@Column(name = "idproductosalida")
	private int idproductosalida;
	
	@Column(name = "idproductoentrada")
	private int idproductoentrada;
	
	@Column(name = "cantidad")
	private float cantidad;
	
	@Column(name = "idmedida")
	private int idmedida;
	
	public int getIdrecetadetalle() {
		return idrecetadetalle;
	}

	public void setIdrecetadetalle(int idrecetadetalle) {
		this.idrecetadetalle = idrecetadetalle;
	}

	public int getIdproductosalida() {
		return idproductosalida;
	}

	public void setIdproductosalida(int idproductosalida) {
		this.idproductosalida = idproductosalida;
	}
	
	public int getIdproductoentrada() {
		return idproductoentrada;
	}

	public void setIdproductoentrada(int idproductoentrada) {
		this.idproductoentrada = idproductoentrada;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public int getIdmedida() {
		return idmedida;
	}

	public void setIdmedida(int idmedida) {
		this.idmedida = idmedida;
	}

	
}
