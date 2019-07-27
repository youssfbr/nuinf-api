package com.alissondev.nuinf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Pessoa> find(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoas.findById(id);
		if (pessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoa.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa save(@RequestBody Pessoa pessoa) {
		return pessoas.save(pessoa);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		pessoas.deleteById(id);
	}
}
