package com.alissondev.nuinf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alissondev.nuinf.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	List<Pessoa> findByNome(String nome);
	Pessoa findByCpf(String cpf);

}
