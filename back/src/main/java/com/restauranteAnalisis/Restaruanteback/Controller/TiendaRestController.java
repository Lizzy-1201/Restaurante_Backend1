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
import com.restauranteAnalisis.Restaruanteback.Entity.Tienda;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.ITiendaRepository;

@RestController
@RequestMapping("/tienda")	
@CrossOrigin("*")
public class TiendaRestController {

	@Autowired
	private ITiendaRepository tiendaRepository;

	@GetMapping
	public List<Tienda> getAllTiendas() {
		return tiendaRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tienda> getTiendaById(@PathVariable(value = "id") int tiendaId)
			throws ResourceNotFoundException {
		Tienda tienda = tiendaRepository.findById(tiendaId)
				.orElseThrow(() -> new ResourceNotFoundException("Tienda not found for this id :: " + tiendaId));
		return ResponseEntity.ok().body(tienda);
	}

	@PostMapping
	public Tienda createTienda(@Validated @RequestBody Tienda tienda) {
		return tiendaRepository.save(tienda);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tienda> updateTienda(@PathVariable(value = "id") int tiendaId,
			@Validated @RequestBody Tienda tiendaDetails) throws ResourceNotFoundException {
		Tienda tienda = tiendaRepository.findById(tiendaId)
				.orElseThrow(() -> new ResourceNotFoundException("Tienda not found for this id :: " + tiendaId));

		tienda.setDireccion(tiendaDetails.getDireccion());
		tienda.setTelefono(tiendaDetails.getTelefono());
		final Tienda updatedTienda = tiendaRepository.save(tienda);
		return ResponseEntity.ok(updatedTienda);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteTienda(@PathVariable(value = "id") int tiendaId)
			throws ResourceNotFoundException {
		Tienda tienda = tiendaRepository.findById(tiendaId)
				.orElseThrow(() -> new ResourceNotFoundException("Tienda not found for this id :: " + tiendaId));

		tiendaRepository.delete(tienda);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
