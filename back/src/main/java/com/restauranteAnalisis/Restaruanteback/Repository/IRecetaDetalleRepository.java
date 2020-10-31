package com.restauranteAnalisis.Restaruanteback.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restauranteAnalisis.Restaruanteback.Entity.Inventario;
import com.restauranteAnalisis.Restaruanteback.Entity.RecetaDetalle;

public interface IRecetaDetalleRepository extends JpaRepository<RecetaDetalle, Integer>{

	@Query("SELECT rd FROM RecetaDetalle rd WHERE rd.idproductoentrada = ?1")
	List<RecetaDetalle> buscarEntrada(int idproductoentrada);
	
	@Query("SELECT rd FROM RecetaDetalle rd WHERE rd.idproductosalida = ?1")
	RecetaDetalle buscarSalida(int idproductosalida);
	
	@Query("SELECT rd FROM RecetaDetalle rd WHERE rd.idrecetadetalle = ?1")
	RecetaDetalle buscarRecetaDetalle(int idrecetadetalle);
	
}
