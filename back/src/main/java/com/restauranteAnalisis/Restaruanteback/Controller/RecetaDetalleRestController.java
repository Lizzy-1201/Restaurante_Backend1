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
import com.restauranteAnalisis.Restaruanteback.Entity.RecetaDetalle;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IRecetaDetalleRepository;

@RestController
@RequestMapping("/receta_detalle")	
@CrossOrigin("*")
public class RecetaDetalleRestController {

	@Autowired
	private IRecetaDetalleRepository receta_detalleRepository;

	@GetMapping
	public List<RecetaDetalle> getAllRecetaDetalles() {
		return receta_detalleRepository.findAll();
	}

	/*@GetMapping("/{id}")
	public ResponseEntity<RecetaDetalle> getRecetaDetalleById(@PathVariable(value = "id") int receta_detalleId)
			throws ResourceNotFoundException {
		RecetaDetalle receta_detalle = receta_detalleRepository.findById(receta_detalleId)
				.orElseThrow(() -> new ResourceNotFoundException("RecetaDetalle not found for this id :: " + receta_detalleId));
		return ResponseEntity.ok().body(receta_detalle);
	}*/
	
	@GetMapping(value = "/listarentrada/{id}")
	public List<RecetaDetalle> buscarEntrada(@PathVariable int id) {
		return receta_detalleRepository.buscarEntrada(id);
	}
	
	@GetMapping(value = "/listarsalida/{id}")
	public RecetaDetalle buscarSalida(@PathVariable int id) {
		return receta_detalleRepository.buscarSalida(id);
	}
	
	@GetMapping(value = "/listarrecetadetalle/{id}")
	public RecetaDetalle buscarRecetaDetalle(@PathVariable int id) {
		return receta_detalleRepository.buscarRecetaDetalle(id);
	}
	
	/*@GetMapping(value = "/borrarreceta_detalle/{id}/{id2}")
	public RecetaDetalle borrarRecetaDetalle(@PathVariable int id, @PathVariable int id2) {
		return receta_detalleRepository.borrarRecetaDetalle(id, id2);
	}*/

	@PostMapping
	public RecetaDetalle createRecetaDetalle(@Validated @RequestBody RecetaDetalle receta_detalle) {
		return receta_detalleRepository.save(receta_detalle);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RecetaDetalle> updateRecetaDetalle(@PathVariable(value = "id") int idrecetadetalle,
			@Validated @RequestBody RecetaDetalle receta_detalleDetails) throws ResourceNotFoundException {
		RecetaDetalle receta_detalle = receta_detalleRepository.buscarRecetaDetalle(idrecetadetalle);
		receta_detalle.setIdproductoentrada(receta_detalleDetails.getIdproductoentrada());
		receta_detalle.setCantidad(receta_detalleDetails.getCantidad());
		receta_detalle.setIdmedida(receta_detalleDetails.getIdmedida());
		final RecetaDetalle updatedRecetaDetalle = receta_detalleRepository.save(receta_detalle);
		return ResponseEntity.ok(updatedRecetaDetalle);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteRecetaDetalle(@PathVariable(value = "id") int idrecetadetalle)
			throws ResourceNotFoundException {
		RecetaDetalle receta_detalle = receta_detalleRepository.buscarRecetaDetalle(idrecetadetalle);

		receta_detalleRepository.delete(receta_detalle);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
