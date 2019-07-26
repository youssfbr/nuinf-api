package com.alissondev.nuinf.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alissondev.nuinf.entities.Pessoa;

@RestController
@RequestMapping("nuinf/pessoas")
public class PessoaController {

	@GetMapping
	public List<Pessoa> findAll() {
		
		Pessoa p1 = new Pessoa();
		p1.setNome("Alisson");
		
		Pessoa p2 = new Pessoa();		
		p2.setNome("Youssf");
		
		Pessoa[] pessoas = { p1, p2 };
		
		return Arrays.asList(pessoas);
		
		
	}
}
