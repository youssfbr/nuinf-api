package com.alissondev.nuinf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alissondev.nuinf.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
