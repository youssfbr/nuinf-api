package com.alissondev.nuinf.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alissondev.nuinf.entities.Pessoa;
import com.alissondev.nuinf.entities.Telefone;
import com.alissondev.nuinf.services.PessoaService;
@CrossOrigin("*")
@RestController
@RequestMapping("nuinf/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping	
	public ResponseEntity<List<Pessoa>> listar() {		
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listar());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable("id") Long id) {
		Optional<Pessoa> pessoa = pessoaService.buscar(id);		
		
		return ResponseEntity.ok(pessoa.get());
	}		
	
	@GetMapping("/nome/{nome}")	
	public List<Pessoa> buscaPessoaPeloNomePV(@PathVariable("nome") String nome) {
		List<Pessoa> pessoa = pessoaService.buscarPeloNome(nome);	   
		return pessoa;
	}
	
	
	
	@GetMapping("/cpf/{cpf}")
	public Pessoa buscaPessoaPeloCpfPV(@PathVariable("cpf") String cpf) {
		Pessoa pessoa = pessoaService.buscarPeloCpf(cpf);	   
		return pessoa;
	}

	
	
	@PostMapping	
	public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa) {
		pessoa = pessoaService.salvar(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		
		return ResponseEntity.created(uri).body(pessoa) ;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {		
		pessoaService.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) {
		
		pessoa.setId(id); // Garante que o que está está sendo atualizado é o que está vindo na URI.
		pessoa = pessoaService.atualizar(pessoa);	

		return ResponseEntity.status(HttpStatus.OK).body(pessoa);	
	}
		
	//Salva telefone
	@PostMapping("/{id}/telefones")	
	public ResponseEntity<Void> adicionarTelefone(@Valid @PathVariable("id") Long pessoaId, @RequestBody Telefone telefone) {
		
		pessoaService.salvarTelefone(pessoaId, telefone);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//Lista telefone
	@GetMapping("/{id}/telefones")
	public ResponseEntity<List<Telefone>> listarTelefones(@PathVariable("id") Long PessoaId) {
		List<Telefone> telefones = pessoaService.listarTelefones(PessoaId);
		
		return ResponseEntity.status(HttpStatus.OK).body(telefones);
	}
	
	@DeleteMapping("/{id}/telefones")
	public void deletarTelefones(@PathVariable("id") Long pessoaId) {
		pessoaService.deletarTelefones(pessoaId);		
	}
}
