package com.restauranteAnalisis.Restaruanteback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restauranteAnalisis.Restaruanteback.Entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
