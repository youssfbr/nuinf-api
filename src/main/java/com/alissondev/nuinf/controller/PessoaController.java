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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alissondev.nuinf.entities.Pessoa;
import com.alissondev.nuinf.repository.PessoaRepository;

@RestController
@RequestMapping("nuinf/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoas;
	
	@GetMapping	
	public List<Pessoa> findAll() {		
		return pessoas.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findByID(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoas.findById(id);
		
		if (pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoa.get());
	}
	
	@PostMapping	
	public ResponseEntity<Void> save(@RequestBody Pessoa pessoa) {
		pessoa = pessoas.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		
		return ResponseEntity.created(uri).build() ;
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		pessoas.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@RequestBody Pessoa pessoa, @PathVariable Long id) {
		pessoa.setId(id);
		pessoas.save(pessoa);
	}
}
