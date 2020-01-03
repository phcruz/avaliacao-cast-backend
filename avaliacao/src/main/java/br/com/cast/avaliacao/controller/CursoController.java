package br.com.cast.avaliacao.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CursoService;

/**
 * Classe controladora de requisições específica de cursos
 * @author paulo
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<List<Curso>> listarCursos() {
		List<Curso> cursos = cursoService.listarCursos();

		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> buscarCursoId(@PathVariable Long id) {
		Curso curso = cursoService.buscarCursoId(id);
			
		return ResponseEntity.ok().body(curso);
	}
	
	@PostMapping
	public ResponseEntity<Curso> inserirCurso(@Valid @RequestBody Curso curso) {
		curso = cursoService.salvarCurso(curso, false);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(curso.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> alterarCurso(@Valid @RequestBody Curso curso) {
		curso = cursoService.salvarCurso(curso, true);
			
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCursoId(@PathVariable Long id) {
		cursoService.deletarCurso(id);
			
		return ResponseEntity.noContent().build();
	}
}
