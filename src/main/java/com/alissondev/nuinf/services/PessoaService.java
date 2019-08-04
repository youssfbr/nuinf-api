package com.alissondev.nuinf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.alissondev.nuinf.entities.Pessoa;
import com.alissondev.nuinf.entities.Telefone;
import com.alissondev.nuinf.repository.PessoaRepository;
import com.alissondev.nuinf.repository.TelefoneRepository;
import com.alissondev.nuinf.services.exceptions.PessoaNaoEncontradaException;

@Service
public class PessoaService {	
		
	@Autowired
	private PessoaRepository pessoasRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Pessoa> listar() {
		return pessoasRepository.findAll();
	}
	
	public Optional<Pessoa> buscar(Long id) {
		Optional<Pessoa> pessoa = pessoasRepository.findById(id);
		
		if (pessoa.isEmpty()) {
			throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
		}
		
		return pessoa;
	}
	
	public List<Pessoa> buscarPeloNome(String nome) {
		List<Pessoa> pessoa = pessoasRepository.findByNome(nome);
		
		if (pessoa.isEmpty()) {
			throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
		}
		
		return pessoa;
	}
	
	public Pessoa buscarPeloCpf(String cpf) {
		Pessoa pessoa = pessoasRepository.findByCpf(cpf);
		
		if (pessoa == null) {
			throw new PessoaNaoEncontradaException("Pessoa não encontrada.");
		}
		
		return pessoa;
	}
		
	public Pessoa salvar(Pessoa pessoa) {
		pessoa.setId(null);
		return pessoasRepository.save(pessoa);
	}
	
	public void deletar(Long id) {
		try {
			pessoasRepository.deleteById(id);	
		} 
		catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException("Pessoa não encontrada");
		}		
	}
	
	public Pessoa atualizar(Pessoa pessoa) {
		verificarExistencia(pessoa);
		return pessoasRepository.save(pessoa);		
	}
	
	private void verificarExistencia(Pessoa pessoa) {
		buscar(pessoa.getId());
	}
	
	public Telefone salvarTelefone(Long pessoaId, Telefone telefone) {
		Pessoa pessoa = buscar(pessoaId).get();
		
		telefone.setPessoa(pessoa);
		
		return telefoneRepository.save(telefone);
	}
	
	public List<Telefone> listarTelefones(Long telefoneId) {
		Pessoa pessoa = buscar(telefoneId).get();
		
		return pessoa.getTelefones();
	}
}
