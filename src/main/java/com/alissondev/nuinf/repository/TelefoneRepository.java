package com.alissondev.nuinf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alissondev.nuinf.entities.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	
	List<Telefone> findByPessoa(long pessoaId);	

}
