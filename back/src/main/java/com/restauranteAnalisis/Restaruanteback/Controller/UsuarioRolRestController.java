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
import com.restauranteAnalisis.Restaruanteback.Entity.UsuarioRol;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IUsuarioRolRepository;

@RestController
@RequestMapping("/usuariorol")	
@CrossOrigin("*")
public class UsuarioRolRestController {

	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;

	@GetMapping
	public List<UsuarioRol> getAllUsuarioRols() {
		return usuarioRolRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioRol> getUsuarioRolById(@PathVariable(value = "id") int usuarioRolId)
			throws ResourceNotFoundException {
		UsuarioRol usuarioRol = usuarioRolRepository.findById(usuarioRolId)
				.orElseThrow(() -> new ResourceNotFoundException("UsuarioRol not found for this id :: " + usuarioRolId));
		return ResponseEntity.ok().body(usuarioRol);
	}

	@PostMapping
	public UsuarioRol createUsuarioRol(@Validated @RequestBody UsuarioRol usuarioRol) {
		return usuarioRolRepository.save(usuarioRol);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioRol> updateUsuarioRol(@PathVariable(value = "id") int usuarioRolId,
			@Validated @RequestBody UsuarioRol usuarioRolDetails) throws ResourceNotFoundException {
		UsuarioRol usuarioRol = usuarioRolRepository.findById(usuarioRolId)
				.orElseThrow(() -> new ResourceNotFoundException("UsuarioRol not found for this id :: " + usuarioRolId));

		usuarioRol.setIdrol(usuarioRolDetails.getIdrol());
		final UsuarioRol updatedUsuarioRol = usuarioRolRepository.save(usuarioRol);
		return ResponseEntity.ok(updatedUsuarioRol);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUsuarioRol(@PathVariable(value = "id") int usuarioRolId)
			throws ResourceNotFoundException {
		UsuarioRol usuarioRol = usuarioRolRepository.findById(usuarioRolId)
				.orElseThrow(() -> new ResourceNotFoundException("UsuarioRol not found for this id :: " + usuarioRolId));

		usuarioRolRepository.delete(usuarioRol);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
