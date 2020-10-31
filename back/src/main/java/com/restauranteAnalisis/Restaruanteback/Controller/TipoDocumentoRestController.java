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
import com.restauranteAnalisis.Restaruanteback.Entity.TipoDocumento;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.ITipoDocumentoRepository;

@RestController
@RequestMapping("/tipoDocumento")	
@CrossOrigin("*")
public class TipoDocumentoRestController {

	@Autowired
	private ITipoDocumentoRepository tipoDocumentoRepository;

	@GetMapping
	public List<TipoDocumento> getAllTipoDocumentos() {
		return tipoDocumentoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoDocumento> getTipoDocumentoById(@PathVariable(value = "id") int tipoDocumentoId)
			throws ResourceNotFoundException {
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(tipoDocumentoId)
				.orElseThrow(() -> new ResourceNotFoundException("TipoDocumento not found for this id :: " + tipoDocumentoId));
		return ResponseEntity.ok().body(tipoDocumento);
	}

	@PostMapping
	public TipoDocumento createTipoDocumento(@Validated @RequestBody TipoDocumento tipoDocumento) {
		return tipoDocumentoRepository.save(tipoDocumento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TipoDocumento> updateTipoDocumento(@PathVariable(value = "id") int tipoDocumentoId,
			@Validated @RequestBody TipoDocumento tipoDocumentoDetails) throws ResourceNotFoundException {
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(tipoDocumentoId)
				.orElseThrow(() -> new ResourceNotFoundException("TipoDocumento not found for this id :: " + tipoDocumentoId));

		tipoDocumento.setDescripcion(tipoDocumentoDetails.getDescripcion());
		final TipoDocumento updatedTipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
		return ResponseEntity.ok(updatedTipoDocumento);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteTipoDocumento(@PathVariable(value = "id") int tipoDocumentoId)
			throws ResourceNotFoundException {
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(tipoDocumentoId)
				.orElseThrow(() -> new ResourceNotFoundException("TipoDocumento not found for this id :: " + tipoDocumentoId));

		tipoDocumentoRepository.delete(tipoDocumento);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
