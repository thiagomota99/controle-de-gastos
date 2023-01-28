package br.com.thgyn.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoriaException extends RuntimeException {
	
	private List<String> erros = new ArrayList<String>();
	
	public CategoriaException(String msg) {
		super(msg);
	}
	
	public void addErro(String msg) {
		this.erros.add(msg);
	}
	
	public List<String> getErros() {
		return Collections.unmodifiableList(this.erros);
	}
}
