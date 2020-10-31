package com.restauranteAnalisis.Restaruanteback.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restauranteAnalisis.Restaruanteback.Entity.Inventario;
import com.restauranteAnalisis.Restaruanteback.Entity.Receta;

public interface IRecetaRepository extends JpaRepository<Receta, Integer>{

	
	
}
