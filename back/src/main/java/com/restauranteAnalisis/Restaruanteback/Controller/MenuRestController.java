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
import com.restauranteAnalisis.Restaruanteback.Entity.Menu;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IMenuRepository;

@RestController
@RequestMapping("/menu")	
@CrossOrigin("*")
public class MenuRestController {

	@Autowired
	private IMenuRepository menuRepository;

	@GetMapping
	public List<Menu> getAllMenus() {
		return menuRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Menu> getMenuById(@PathVariable(value = "id") int menuId)
			throws ResourceNotFoundException {
		Menu menu = menuRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + menuId));
		return ResponseEntity.ok().body(menu);
	}

	@PostMapping
	public Menu createMenu(@Validated @RequestBody Menu menu) {
		return menuRepository.save(menu);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Menu> updateMenu(@PathVariable(value = "id") int menuId,
			@Validated @RequestBody Menu menuDetails) throws ResourceNotFoundException {
		Menu menu = menuRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + menuId));

		menu.setDescripcion(menuDetails.getDescripcion());
		menu.setId_menu_padre(menuDetails.getId_menu_padre());
		menu.setUrl(menuDetails.getUrl());
		
		final Menu updatedMenu = menuRepository.save(menu);
		return ResponseEntity.ok(updatedMenu);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteMenu(@PathVariable(value = "id") int menuId)
			throws ResourceNotFoundException {
		Menu menu = menuRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + menuId));

		menuRepository.delete(menu);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
