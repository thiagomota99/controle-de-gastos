package br.com.thgyn.modelo.services;

import java.util.List;


import br.com.thgyn.validadores.Validador;

public interface ServiceCRUD<T> {
	
	public void adicionar(T obj, Validador<T> validacoes);
	
	public List<T> listar();
	
	public T buscar(Integer id);
	
	public void atualizar(T categoria, Validador<T> validacoes);
	
	public void deletar(Integer id);
}
