package com.restauranteAnalisis.Restaruanteback.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.restauranteAnalisis.Restaruanteback.Entity.TransaccionInventarioDetalle;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.ITransaccionInventarioDetalleRepository;


@RestController
@RequestMapping("/transaccioninventariodetalle")	
@CrossOrigin("*")
public class TransaccionInventarioDetalleRestController {

	@Autowired
	private ITransaccionInventarioDetalleRepository transaccionInventarioDetalleRepository;

	@GetMapping
	public List<TransaccionInventarioDetalle> getAllTransaccionInventarioDetalles() {
		return transaccionInventarioDetalleRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransaccionInventarioDetalle> getTransaccionInventarioDetalleById(@PathVariable(value = "id") int transaccionInventarioDetalleId)
			throws ResourceNotFoundException {
		TransaccionInventarioDetalle transaccionInventarioDetalle = transaccionInventarioDetalleRepository.findById(transaccionInventarioDetalleId)
				.orElseThrow(() -> new ResourceNotFoundException("TransaccionInventarioDetalle not found for this id :: " + transaccionInventarioDetalleId));
		return ResponseEntity.ok().body(transaccionInventarioDetalle);
	}
	
	@GetMapping(value = "/listarempleado/{id}")
	public List<TransaccionInventarioDetalle> buscarEmpleado(@PathVariable int id) {
		return transaccionInventarioDetalleRepository.buscarEmpleado(id);
	}
	
	@GetMapping(value = "/listarfecha/{id}")
	public List<TransaccionInventarioDetalle> buscarFecha(@PathVariable String id) throws java.text.ParseException {
		return transaccionInventarioDetalleRepository.buscarFecha(id);
	}

	@PostMapping
	public TransaccionInventarioDetalle createTransaccionInventarioDetalle(@Validated @RequestBody TransaccionInventarioDetalle transaccionInventarioDetalle) {
		return transaccionInventarioDetalleRepository.save(transaccionInventarioDetalle);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransaccionInventarioDetalle> updateTransaccionInventarioDetalle(@PathVariable(value = "id") int transaccionInventarioDetalleId,
			@Validated @RequestBody TransaccionInventarioDetalle transaccionInventarioDetalleDetails) throws ResourceNotFoundException {
		TransaccionInventarioDetalle transaccionInventarioDetalle = transaccionInventarioDetalleRepository.findById(transaccionInventarioDetalleId)
				.orElseThrow(() -> new ResourceNotFoundException("TransaccionInventarioDetalle not found for this id :: " + transaccionInventarioDetalleId));

		transaccionInventarioDetalle.setIdempleado(transaccionInventarioDetalleDetails.getIdempleado());
		transaccionInventarioDetalle.setIdproducto(transaccionInventarioDetalleDetails.getIdproducto());
		transaccionInventarioDetalle.setUnidades(transaccionInventarioDetalleDetails.getUnidades());
		transaccionInventarioDetalle.setCostototal(transaccionInventarioDetalleDetails.getCostototal());
		transaccionInventarioDetalle.setFecha(transaccionInventarioDetalleDetails.getFecha());
		final TransaccionInventarioDetalle updatedTransaccionInventarioDetalle = transaccionInventarioDetalleRepository.save(transaccionInventarioDetalle);
		return ResponseEntity.ok(updatedTransaccionInventarioDetalle);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteTransaccionInventarioDetalle(@PathVariable(value = "id") int transaccionInventarioDetalleId)
			throws ResourceNotFoundException {
		TransaccionInventarioDetalle transaccionInventarioDetalle = transaccionInventarioDetalleRepository.findById(transaccionInventarioDetalleId)
				.orElseThrow(() -> new ResourceNotFoundException("TransaccionInventarioDetalle not found for this id :: " + transaccionInventarioDetalleId));

		transaccionInventarioDetalleRepository.delete(transaccionInventarioDetalle);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
