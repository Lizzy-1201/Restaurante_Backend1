package com.restauranteAnalisis.Restaruanteback.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restauranteAnalisis.Restaruanteback.Entity.RecetaDetalle;
import com.restauranteAnalisis.Restaruanteback.Entity.TransaccionInventarioDetalle;

public interface ITransaccionInventarioDetalleRepository extends JpaRepository<TransaccionInventarioDetalle, Integer>{

	@Query("SELECT tid FROM TransaccionInventarioDetalle tid WHERE tid.idempleado = ?1")
	List<TransaccionInventarioDetalle> buscarEmpleado(int idempleado);
	
	@Query("SELECT tid FROM TransaccionInventarioDetalle tid WHERE tid.fecha = ?1")
	List<TransaccionInventarioDetalle> buscarFecha(String fecha);
	
}
