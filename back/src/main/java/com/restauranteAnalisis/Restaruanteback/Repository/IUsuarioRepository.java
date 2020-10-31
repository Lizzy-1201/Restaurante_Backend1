package com.restauranteAnalisis.Restaruanteback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restauranteAnalisis.Restaruanteback.Entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

}
