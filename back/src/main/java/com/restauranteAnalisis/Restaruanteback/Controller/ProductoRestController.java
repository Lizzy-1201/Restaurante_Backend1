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
import com.restauranteAnalisis.Restaruanteback.Entity.Producto;
import com.restauranteAnalisis.Restaruanteback.Exception.ResourceNotFoundException;
import com.restauranteAnalisis.Restaruanteback.Repository.IProductoRepository;

@RestController
@RequestMapping("/producto")	
@CrossOrigin("*")
public class ProductoRestController {

	@Autowired
	private IProductoRepository productoRepository;

	@GetMapping
	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable(value = "id") int productoId)
			throws ResourceNotFoundException {
		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new ResourceNotFoundException("Producto not found for this id :: " + productoId));
		return ResponseEntity.ok().body(producto);
	}

	@PostMapping
	public Producto createProducto(@Validated @RequestBody Producto producto) {
		return productoRepository.save(producto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable(value = "id") int productoId,
			@Validated @RequestBody Producto productoDetails) throws ResourceNotFoundException {
		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new ResourceNotFoundException("Producto not found for this id :: " + productoId));

		producto.setDescripcion(productoDetails.getDescripcion());
		producto.setParaventa(productoDetails.getParaventa());
		producto.setEsproducido(productoDetails.getEsproducido());
		producto.setIdmedida(productoDetails.getIdmedida());
		final Producto updatedProducto = productoRepository.save(producto);
		return ResponseEntity.ok(updatedProducto);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProducto(@PathVariable(value = "id") int productoId)
			throws ResourceNotFoundException {
		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new ResourceNotFoundException("Producto not found for this id :: " + productoId));

		productoRepository.delete(producto);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
