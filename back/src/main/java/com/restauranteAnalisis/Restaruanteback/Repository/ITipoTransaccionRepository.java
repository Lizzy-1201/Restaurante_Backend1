package com.restauranteAnalisis.Restaruanteback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restauranteAnalisis.Restaruanteback.Entity.TipoTransaccion;

public interface ITipoTransaccionRepository extends JpaRepository<TipoTransaccion, Integer>{

}
