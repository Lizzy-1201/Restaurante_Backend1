package com.restauranteAnalisis.Restaruanteback.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restauranteAnalisis.Restaruanteback.Entity.Inventario;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer>{
	
	@Query("SELECT inv FROM Inventario inv WHERE inv.idtienda = ?1")
	List<Inventario> buscarTienda(int idtienda);
	
	@Query("SELECT inv FROM Inventario inv WHERE inv.idtienda = ?1 and inv.idproducto = ?2")
	Inventario buscarInventario(int idtienda, int idproducto);
	
	@Query("SELECT inv FROM Inventario inv, Tienda tie, Empleado emp "
			+ "WHERE inv.idtienda = tie.idtienda AND tie.idtienda = emp.idtienda AND emp.idempleado = ?1")
	List<Inventario> buscarEmpleado(int idempleado);
	
}
