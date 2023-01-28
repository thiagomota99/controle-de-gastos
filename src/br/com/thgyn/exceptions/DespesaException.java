package br.com.thgyn.exceptions;

import java.util.Collections;
import java.util.List;

public class DespesaException extends EntidadeException {
	
	private static final long serialVersionUID = 1L;
	
	public DespesaException(String msg) {
		super(msg);
	}

	@Override
	public void addErro(String msg) {
		super.erros.add(msg);
	}

	@Override
	public List<String> getErros() {
		return Collections.unmodifiableList(super.erros);
	}
}
