package br.com.cast.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;

/**
 * Classe controladora de requisições específica de categorias
 * @author paulo
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> categorias = categoriaService.listarCategorias();
			
		return ResponseEntity.ok().body(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoriaId(@PathVariable Long id) {
		Categoria categorias = categoriaService.buscarCategoriaId(id);
			
		return ResponseEntity.ok().body(categorias);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.salvarCategoria(categoria);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
	}
	
	@PutMapping
	public ResponseEntity<Categoria> alterarCategoria(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.salvarCategoria(categoria);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCategoriaId(@PathVariable Long id) {
		categoriaService.deletarCategoria(id);
			
		return ResponseEntity.noContent().build();
	}
}
