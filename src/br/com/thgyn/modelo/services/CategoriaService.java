package br.com.thgyn.modelo.services;

import java.util.List;

import br.com.thgyn.dao.categoria.Persistivel;
import br.com.thgyn.modelo.entidades.Categoria;

public class CategoriaService {
	
	Persistivel<Categoria> repository;
	
	public CategoriaService() {
		
	}
	
	public CategoriaService(Persistivel<Categoria> repository) {
		this.repository = repository;
	}
	
	public void adicionar(Categoria categoria) {
		if(categoria == null)
			throw new IllegalArgumentException("O objeto categoria é nulo");
		if(categoria.getId() != null)
			throw new IllegalArgumentException("O identificador de novas categorias deve ser nulo");
		if(categoria.getNome() == null || categoria.getNome().trim().isEmpty())
			throw new IllegalArgumentException("A categoria precisa de um nome.");
		
		repository.adicionar(categoria);
	}
	
	public List<Categoria> listar(){
		return repository.listar();
	}
}
