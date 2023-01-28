package br.com.thgyn.exceptions;

import java.util.Collections;
import java.util.List;

public class CategoriaException extends EntidadeException {
	
	private static final long serialVersionUID = 1L;
	
	public CategoriaException(String msg) {
		super(msg);
	}
	
	public void addErro(String msg) {
		super.erros.add(msg);
	}
	
	public List<String> getErros() {
		return Collections.unmodifiableList(super.erros);
	}
}
