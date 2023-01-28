package br.com.thgyn.exceptions;

import java.util.ArrayList;
import java.util.List;

public abstract class EntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	protected List<String> erros = new ArrayList<String>();

	public EntidadeException(String msg) {
		super(msg);
	}
	
	public abstract void addErro(String msg);
	
	public abstract List<String> getErros();
}
