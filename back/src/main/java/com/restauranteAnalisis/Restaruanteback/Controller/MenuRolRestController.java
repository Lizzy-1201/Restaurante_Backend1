package com.restauranteAnalisis.Restaruanteback.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restauranteAnalisis.Restaruanteback.Entity.MenuRol;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IMenuRolRepository;

@RestController
@RequestMapping("/menurol")	
@CrossOrigin("*")
public class MenuRolRestController {

	@Autowired
	private IMenuRolRepository menuRolRepository;

	@GetMapping
	public List<MenuRol> getAllMenuRols() {
		return menuRolRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<MenuRol> getMenuRolById(@PathVariable(value = "id") int menuRolId)
			throws ResourceNotFoundException {
		MenuRol menuRol = menuRolRepository.findById(menuRolId)
				.orElseThrow(() -> new ResourceNotFoundException("MenuRol not found for this id :: " + menuRolId));
		return ResponseEntity.ok().body(menuRol);
	}

	@PostMapping
	public MenuRol createMenuRol(@Validated @RequestBody MenuRol menuRol) {
		return menuRolRepository.save(menuRol);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MenuRol> updateMenuRol(@PathVariable(value = "id") int menuRolId,
			@Validated @RequestBody MenuRol menuRolDetails) throws ResourceNotFoundException {
		MenuRol menuRol = menuRolRepository.findById(menuRolId)
				.orElseThrow(() -> new ResourceNotFoundException("MenuRol not found for this id :: " + menuRolId));

		menuRol.setIdrol(menuRolDetails.getIdrol());
		final MenuRol updatedMenuRol = menuRolRepository.save(menuRol);
		return ResponseEntity.ok(updatedMenuRol);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteMenuRol(@PathVariable(value = "id") int menuRolId)
			throws ResourceNotFoundException {
		MenuRol menuRol = menuRolRepository.findById(menuRolId)
				.orElseThrow(() -> new ResourceNotFoundException("MenuRol not found for this id :: " + menuRolId));

		menuRolRepository.delete(menuRol);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
