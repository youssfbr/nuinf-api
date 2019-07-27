package com.alissondev.nuinf.services.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException(String messagem) {
		super(messagem);
	}
	
	public PessoaNaoEncontradaException(String messagem, Throwable causa) {
		super(messagem, causa);
	}
}
