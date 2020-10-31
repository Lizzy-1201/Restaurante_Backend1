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
import com.restauranteAnalisis.Restaruanteback.Entity.Inventario;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IInventarioRepository;

@RestController
@RequestMapping("/inventario")	
@CrossOrigin("*")
public class InventarioRestController {

	@Autowired
	private IInventarioRepository inventarioRepository;

	@GetMapping
	public List<Inventario> getAllInventarios() {
		return inventarioRepository.findAll();
	}
	
	@GetMapping(value = "/obtenerinventario/{id}/{id2}")
	public Inventario buscarInventario(@PathVariable int id, @PathVariable int id2) {
		return inventarioRepository.buscarInventario(id, id2);
	}
	
	@GetMapping(value = "/listartienda/{id}")
	public List<Inventario> buscarTienda(@PathVariable int id) {
		return inventarioRepository.buscarTienda(id);
	}
	
	@GetMapping(value = "/listarempleado/{id}")
	public List<Inventario> buscarEmpleado(@PathVariable int id) {
		return inventarioRepository.buscarEmpleado(id);
	}

	@PostMapping
	public Inventario createInventario(@Validated @RequestBody Inventario inventario) {
		return inventarioRepository.save(inventario);
	}
	
	@PutMapping("/{id1}/{id2}")
	public ResponseEntity<Inventario> updateInventario(@PathVariable(value = "id1") int idtienda, @PathVariable(value = "id2") int idproducto,
			@Validated @RequestBody Inventario inventarioDetails) throws ResourceNotFoundException {
		Inventario inventario = inventarioRepository.buscarInventario(idtienda, idproducto);
		inventario.setIdtienda(inventarioDetails.getIdtienda());
		inventario.setIdproducto(inventarioDetails.getIdproducto());
		inventario.setSaldo(inventarioDetails.getSaldo());
		final Inventario updatedInventario = inventarioRepository.save(inventario);
		return ResponseEntity.ok(updatedInventario);
	}

	@DeleteMapping("/{id}/{id2}")
	public Map<String, Boolean> deleteInventario(@PathVariable(value = "id") int idtienda, @PathVariable(value = "id2") int idproducto)
			throws ResourceNotFoundException {
		Inventario inventario = inventarioRepository.buscarInventario(idtienda, idproducto);

		inventarioRepository.delete(inventario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
