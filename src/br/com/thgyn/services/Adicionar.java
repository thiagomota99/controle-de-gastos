package br.com.thgyn.services;

import br.com.thgyn.validadores.Adicionavel;

public interface Adicionar<T> {
	
	public void adicionar(T obj, Adicionavel<T> validacoes);
}
