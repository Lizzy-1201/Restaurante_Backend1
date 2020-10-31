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
import com.restauranteAnalisis.Restaruanteback.Entity.Proveedor;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IProveedorRepository;

@RestController
@RequestMapping("/proveedor")	
@CrossOrigin("*")
public class ProveedorRestController {

	@Autowired
	private IProveedorRepository proveedorRepository;

	@GetMapping
	public List<Proveedor> getAllProveedors() {
		return proveedorRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Proveedor> getProveedorById(@PathVariable(value = "id") int proveedorId)
			throws ResourceNotFoundException {
		Proveedor proveedor = proveedorRepository.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor not found for this id :: " + proveedorId));
		return ResponseEntity.ok().body(proveedor);
	}

	@PostMapping
	public Proveedor createProveedor(@Validated @RequestBody Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Proveedor> updateProveedor(@PathVariable(value = "id") int proveedorId,
			@Validated @RequestBody Proveedor proveedorDetails) throws ResourceNotFoundException {
		Proveedor proveedor = proveedorRepository.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor not found for this id :: " + proveedorId));

		proveedor.setNombre(proveedorDetails.getNombre());
		final Proveedor updatedProveedor = proveedorRepository.save(proveedor);
		return ResponseEntity.ok(updatedProveedor);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProveedor(@PathVariable(value = "id") int proveedorId)
			throws ResourceNotFoundException {
		Proveedor proveedor = proveedorRepository.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor not found for this id :: " + proveedorId));

		proveedorRepository.delete(proveedor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
