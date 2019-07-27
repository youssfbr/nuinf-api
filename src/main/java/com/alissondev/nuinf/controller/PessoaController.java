package com.alissondev.nuinf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping
	public void save(@RequestBody Pessoa pessoa) {
		pessoas.save(pessoa);
	}
}
