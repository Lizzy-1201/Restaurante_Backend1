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
import com.restauranteAnalisis.Restaruanteback.Entity.TipoTransaccion;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.ITipoTransaccionRepository;

@RestController
@RequestMapping("/tipoTransaccion")	
@CrossOrigin("*")
public class TipoTransaccionRestController {

	@Autowired
	private ITipoTransaccionRepository tipoTransaccionRepository;

	@GetMapping
	public List<TipoTransaccion> getAllTipoTransaccions() {
		return tipoTransaccionRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoTransaccion> getTipoTransaccionById(@PathVariable(value = "id") int tipoTransaccionId)
			throws ResourceNotFoundException {
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(tipoTransaccionId)
				.orElseThrow(() -> new ResourceNotFoundException("TipoTransaccion not found for this id :: " + tipoTransaccionId));
		return ResponseEntity.ok().body(tipoTransaccion);
	}

	@PostMapping
	public TipoTransaccion createTipoTransaccion(@Validated @RequestBody TipoTransaccion tipoTransaccion) {
		return tipoTransaccionRepository.save(tipoTransaccion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoTransaccion> updateTipoTransaccion(@PathVariable(value = "id") int tipoTransaccionId,
			@Validated @RequestBody TipoTransaccion tipoTransaccionDetails) throws ResourceNotFoundException {
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(tipoTransaccionId)
				.orElseThrow(() -> new ResourceNotFoundException("TipoTransaccion not found for this id :: " + tipoTransaccionId));

		tipoTransaccion.setDescripcion(tipoTransaccionDetails.getDescripcion());
		tipoTransaccion.setFactor(tipoTransaccionDetails.getFactor());
		final TipoTransaccion updatedTipoTransaccion = tipoTransaccionRepository.save(tipoTransaccion);
		return ResponseEntity.ok(updatedTipoTransaccion);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteTipoTransaccion(@PathVariable(value = "id") int tipoTransaccionId)
			throws ResourceNotFoundException {
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(tipoTransaccionId)
				.orElseThrow(() -> new ResourceNotFoundException("TipoTransaccion not found for this id :: " + tipoTransaccionId));

		tipoTransaccionRepository.delete(tipoTransaccion);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
