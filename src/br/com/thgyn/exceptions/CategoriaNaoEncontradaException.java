package br.com.thgyn.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradaException(String msg) {
		super(msg);
	}
}
