package com.alissondev.nuinf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alissondev.nuinf.entities.Pessoa;
import com.alissondev.nuinf.repository.PessoaRepository;
import com.alissondev.nuinf.services.exceptions.PessoaNaoEncontradaException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoasRepository;
	
	public List<Pessoa> findAll() {
		return pessoasRepository.findAll();
	}
	
	public Optional<Pessoa> findById(Long id) {
		Optional<Pessoa> pessoa = pessoasRepository.findById(id);
		
		if (pessoa.isEmpty()) {
			throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
		}
		return pessoa;
	}
	
	public Pessoa save(Pessoa pessoa) {
		pessoa.setId(null);
		return pessoasRepository.save(pessoa);
	}
	
	public void deleteById(Long id) {
		try {
			pessoasRepository.deleteById(id);	
		} 
		catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException("Pessoa não encontrada");
		}		
	}
	
	public void update(Pessoa pessoa) {
		verificarExistencia(pessoa);
		pessoasRepository.save(pessoa);
	}
	
	private void verificarExistencia(Pessoa pessoa) {
		findById(pessoa.getId());
	}
}
