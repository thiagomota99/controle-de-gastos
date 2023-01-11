package br.com.thgyn.dao.categoria;

import java.util.List;

public interface Persistivel<T> {
	
	public void adicionar(T obj);
	
	public List<T> listar();
	
	public T buscar(Integer id);
	
	public void atualizar(T obj);
	
	public void deletar(Integer id);
}
