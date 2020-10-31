package com.restauranteAnalisis.Restaruanteback.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="receta")
public class Receta {

	@Id
	@Column(name = "idproductosalida")
	private int idproductosalida;

	public int getIdproductosalida() {
		return idproductosalida;
	}

	public void setIdproductosalida(int idproductosalida) {
		this.idproductosalida = idproductosalida;
	}
	
	
}
