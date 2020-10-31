package com.restauranteAnalisis.Restaruanteback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restauranteAnalisis.Restaruanteback.Entity.TransaccionInventario;

public interface ITransaccionInventarioRepository extends JpaRepository<TransaccionInventario, Integer>{

}
