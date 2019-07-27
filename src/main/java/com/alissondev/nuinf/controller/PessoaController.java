package com.alissondev.nuinf.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alissondev.nuinf.entities.Pessoa;
import com.alissondev.nuinf.services.PessoaService;

@RestController
@RequestMapping("nuinf/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping	
	public ResponseEntity<List<Pessoa>> findAll() {		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findByID(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.findById(id);				
		return ResponseEntity.ok(pessoa.get());
	}
	
	@PostMapping	
	public ResponseEntity<Void> save(@RequestBody Pessoa pessoa) {
		pessoa = pessoaService.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		
		return ResponseEntity.created(uri).build() ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {		
		pessoaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Pessoa pessoa, @PathVariable Long id) {
		
		pessoa.setId(id); // Garante que o que está está sendo atualizado é o que está vindo na URI.
		pessoaService.update(pessoa);		
		
		return ResponseEntity.noContent().build();
	}
}
