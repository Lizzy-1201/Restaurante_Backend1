package com.restauranteAnalisis.Restaruanteback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restauranteAnalisis.Restaruanteback.Entity.Menu;

public interface IMenuRepository extends JpaRepository<Menu, Integer>{

}
