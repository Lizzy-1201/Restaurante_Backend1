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
import com.restauranteAnalisis.Restaruanteback.Entity.Rol;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IRolRepository;

@RestController
@RequestMapping("/rol")	
@CrossOrigin("*")
public class RolRestController {

	@Autowired
	private IRolRepository rolRepository;

	@GetMapping
	public List<Rol> getAllRols() {
		return rolRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rol> getRolById(@PathVariable(value = "id") int rolId)
			throws ResourceNotFoundException {
		Rol rol = rolRepository.findById(rolId)
				.orElseThrow(() -> new ResourceNotFoundException("Rol not found for this id :: " + rolId));
		return ResponseEntity.ok().body(rol);
	}

	@PostMapping
	public Rol createRol(@Validated @RequestBody Rol rol) {
		return rolRepository.save(rol);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Rol> updateRol(@PathVariable(value = "id") int rolId,
			@Validated @RequestBody Rol rolDetails) throws ResourceNotFoundException {
		Rol rol = rolRepository.findById(rolId)
				.orElseThrow(() -> new ResourceNotFoundException("Rol not found for this id :: " + rolId));

		rol.setDescripcion(rolDetails.getDescripcion());
		rol.setActivo(rolDetails.getActivo());
		final Rol updatedRol = rolRepository.save(rol);
		return ResponseEntity.ok(updatedRol);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteRol(@PathVariable(value = "id") int rolId)
			throws ResourceNotFoundException {
		Rol rol = rolRepository.findById(rolId)
				.orElseThrow(() -> new ResourceNotFoundException("Rol not found for this id :: " + rolId));

		rolRepository.delete(rol);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
