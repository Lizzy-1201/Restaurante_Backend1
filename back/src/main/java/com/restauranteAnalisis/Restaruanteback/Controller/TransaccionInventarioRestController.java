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
import com.restauranteAnalisis.Restaruanteback.Entity.TransaccionInventario;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.ITransaccionInventarioRepository;

@RestController
@RequestMapping("/transaccioninventario")
@CrossOrigin("*")
public class TransaccionInventarioRestController {

	@Autowired
	private ITransaccionInventarioRepository transaccionInventarioRepository;

	@GetMapping
	public List<TransaccionInventario> getAllTransaccionInventarios() {
		return transaccionInventarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransaccionInventario> getTransaccionInventarioById(@PathVariable(value = "id") int transaccionInventarioId)
			throws ResourceNotFoundException {
		TransaccionInventario transaccionInventario = transaccionInventarioRepository.findById(transaccionInventarioId)
				.orElseThrow(() -> new ResourceNotFoundException("TransaccionInventario not found for this id :: " + transaccionInventarioId));
		return ResponseEntity.ok().body(transaccionInventario);
	}

	@PostMapping
	public TransaccionInventario createTransaccionInventario(@Validated @RequestBody TransaccionInventario transaccionInventario) {
		return transaccionInventarioRepository.save(transaccionInventario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransaccionInventario> updateTransaccionInventario(@PathVariable(value = "id") int transaccionInventarioId,
			@Validated @RequestBody TransaccionInventario transaccionInventarioDetails) throws ResourceNotFoundException {
		TransaccionInventario transaccionInventario = transaccionInventarioRepository.findById(transaccionInventarioId)
				.orElseThrow(() -> new ResourceNotFoundException("TransaccionInventario not found for this id :: " + transaccionInventarioId));

		transaccionInventario.setIdempleado(transaccionInventarioDetails.getIdempleado());
		final TransaccionInventario updatedTransaccionInventario = transaccionInventarioRepository.save(transaccionInventario);
		return ResponseEntity.ok(updatedTransaccionInventario);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteTransaccionInventario(@PathVariable(value = "id") int transaccionInventarioId)
			throws ResourceNotFoundException {
		TransaccionInventario transaccionInventario = transaccionInventarioRepository.findById(transaccionInventarioId)
				.orElseThrow(() -> new ResourceNotFoundException("TransaccionInventario not found for this id :: " + transaccionInventarioId));

		transaccionInventarioRepository.delete(transaccionInventario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
