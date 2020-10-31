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
import com.restauranteAnalisis.Restaruanteback.Entity.Empleado;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IEmpleadoRepository;

@RestController
@RequestMapping("/empleado")	
@CrossOrigin("*")
public class EmpleadoRestController {

	@Autowired
	private IEmpleadoRepository empleadoRepository;

	@GetMapping
	public List<Empleado> getAllEmpleados() {
		return empleadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getEmpleadoById(@PathVariable(value = "id") int empleadoId)
			throws ResourceNotFoundException {
		Empleado empleado = empleadoRepository.findById(empleadoId)
				.orElseThrow(() -> new ResourceNotFoundException("Empleado not found for this id :: " + empleadoId));
		return ResponseEntity.ok().body(empleado);
	}

	@PostMapping
	public Empleado createEmpleado(@Validated @RequestBody Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Empleado> updateEmpleado(@PathVariable(value = "id") int empleadoId,
			@Validated @RequestBody Empleado empleadoDetails) throws ResourceNotFoundException {
		Empleado empleado = empleadoRepository.findById(empleadoId)
				.orElseThrow(() -> new ResourceNotFoundException("Empleado not found for this id :: " + empleadoId));

		empleado.setIdtienda(empleadoDetails.getIdtienda());
		empleado.setPnombre(empleadoDetails.getPnombre());
		empleado.setSnombre(empleadoDetails.getSnombre());
		empleado.setPapelli(empleadoDetails.getPapelli());
		empleado.setSapelli(empleadoDetails.getSapelli());
		final Empleado updatedEmpleado = empleadoRepository.save(empleado);
		return ResponseEntity.ok(updatedEmpleado);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmpleado(@PathVariable(value = "id") int empleadoId)
			throws ResourceNotFoundException {
		Empleado empleado = empleadoRepository.findById(empleadoId)
				.orElseThrow(() -> new ResourceNotFoundException("Empleado not found for this id :: " + empleadoId));

		empleadoRepository.delete(empleado);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
