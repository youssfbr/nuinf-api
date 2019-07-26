package com.alissondev.nuinf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nuinf/pessoas")
public class PessoaController {

	@GetMapping
	public String findAll() {
		return "Hello NUINF";
	}
}
