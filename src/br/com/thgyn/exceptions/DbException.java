package br.com.thgyn.exceptions;

public class DbException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DbException(String mensagem) {
		super(mensagem);
	}
}
