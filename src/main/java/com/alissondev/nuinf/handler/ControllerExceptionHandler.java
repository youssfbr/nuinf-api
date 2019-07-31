package com.alissondev.nuinf.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alissondev.nuinf.entities.DetalhesErro;
import com.alissondev.nuinf.services.exceptions.PessoaNaoEncontradaException;

/*
 * Esta classe é responsável por interceptar e tratar todas as exceções, diminuindo o uso
 * dos Try catch que poluem com muito código, além deste método poder enviar uma mensagem 
 * sobre detalhes dos erros no corpo.
 * 
 * */

@ControllerAdvice
public class ControllerExceptionHandler {	
	
	@ExceptionHandler(PessoaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handlePessoaNaoEncontradaException
					(PessoaNaoEncontradaException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo("Pessoa não encontrada.");
		erro.setMensagemDesenvolvedor("https://nuinf.com/404");
		erro.setTimestamp(System.currentTimeMillis());		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
