package br.com.thgyn.services;

import br.com.thgyn.validadores.Validador;

public interface Atualizavel<T> {
	
	public void atualizar(T categoria, Validador<T> validacoes);
}
