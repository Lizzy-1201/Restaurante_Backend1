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
import com.restauranteAnalisis.Restaruanteback.Entity.PeriodoContable;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IPeriodoContableRepository;

@RestController
@RequestMapping("/periodoContable")	
@CrossOrigin("*")
public class PeriodoContableRestController {

	@Autowired
	private IPeriodoContableRepository periodoContableRepository;

	@GetMapping
	public List<PeriodoContable> getAllPeriodoContables() {
		return periodoContableRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PeriodoContable> getPeriodoContableById(@PathVariable(value = "id") int periodoContableId)
			throws ResourceNotFoundException {
		PeriodoContable periodoContable = periodoContableRepository.findById(periodoContableId)
				.orElseThrow(() -> new ResourceNotFoundException("PeriodoContable not found for this id :: " + periodoContableId));
		return ResponseEntity.ok().body(periodoContable);
	}

	@PostMapping
	public PeriodoContable createPeriodoContable(@Validated @RequestBody PeriodoContable periodoContable) {
		return periodoContableRepository.save(periodoContable);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PeriodoContable> updatePeriodoContable(@PathVariable(value = "id") int periodoContableId,
			@Validated @RequestBody PeriodoContable periodoContableDetails) throws ResourceNotFoundException {
		PeriodoContable periodoContable = periodoContableRepository.findById(periodoContableId)
				.orElseThrow(() -> new ResourceNotFoundException("PeriodoContable not found for this id :: " + periodoContableId));

		periodoContable.setAnio(periodoContableDetails.getAnio());
		periodoContable.setInicio(periodoContableDetails.getInicio());
		periodoContable.setFin(periodoContableDetails.getFin());
		periodoContable.setCerrado(periodoContableDetails.getCerrado());
		final PeriodoContable updatedPeriodoContable = periodoContableRepository.save(periodoContable);
		return ResponseEntity.ok(updatedPeriodoContable);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletePeriodoContable(@PathVariable(value = "id") int periodoContableId)
			throws ResourceNotFoundException {
		PeriodoContable periodoContable = periodoContableRepository.findById(periodoContableId)
				.orElseThrow(() -> new ResourceNotFoundException("PeriodoContable not found for this id :: " + periodoContableId));

		periodoContableRepository.delete(periodoContable);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
