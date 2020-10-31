package com.restauranteAnalisis.Restaruanteback.Entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaccion_inventario")
public class TransaccionInventario {

	@Id
	@Column(name = "idempleado")
	private int idempleado;

	public int getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	
	
}
