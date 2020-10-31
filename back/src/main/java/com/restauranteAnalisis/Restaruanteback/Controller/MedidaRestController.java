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
import com.restauranteAnalisis.Restaruanteback.Entity.Medida;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IMedidaRepository;

@RestController
@RequestMapping("/medida")	
@CrossOrigin("*")
public class MedidaRestController {

	@Autowired
	private IMedidaRepository medidaRepository;

	@GetMapping
	public List<Medida> getAllMedidas() {
		return medidaRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medida> getMedidaById(@PathVariable(value = "id") int medidaId)
			throws ResourceNotFoundException {
		Medida medida = medidaRepository.findById(medidaId)
				.orElseThrow(() -> new ResourceNotFoundException("Medida not found for this id :: " + medidaId));
		return ResponseEntity.ok().body(medida);
	}

	@PostMapping
	public Medida createMedida(@Validated @RequestBody Medida medida) {
		return medidaRepository.save(medida);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Medida> updateMedida(@PathVariable(value = "id") int medidaId,
			@Validated @RequestBody Medida medidaDetails) throws ResourceNotFoundException {
		Medida medida = medidaRepository.findById(medidaId)
				.orElseThrow(() -> new ResourceNotFoundException("Medida not found for this id :: " + medidaId));

		medida.setDescripcion(medidaDetails.getDescripcion());
		final Medida updatedMedida = medidaRepository.save(medida);
		return ResponseEntity.ok(updatedMedida);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteMedida(@PathVariable(value = "id") int medidaId)
			throws ResourceNotFoundException {
		Medida medida = medidaRepository.findById(medidaId)
				.orElseThrow(() -> new ResourceNotFoundException("Medida not found for this id :: " + medidaId));

		medidaRepository.delete(medida);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
