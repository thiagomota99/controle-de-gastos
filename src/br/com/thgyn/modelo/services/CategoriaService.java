package br.com.thgyn.modelo.services;

import java.util.List;

import br.com.thgyn.dao.Persistivel;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class CategoriaService {
	
	Persistivel<Categoria> repository;
	
	public CategoriaService() {
		
	}
	
	public CategoriaService(Persistivel<Categoria> repository) {
		this.repository = repository;
	}
	
	public void adicionar(Categoria obj, Validador<Categoria> validacoes) {
		validacoes.aplicar(obj);
		repository.adicionar(obj);
	}
	
	public List<Categoria> listar(){
		return repository.listar();
	}
	
	public Categoria buscar(Integer id) {
		if(id == null || id <= 0)
			throw new IllegalArgumentException("O objeto: Id é nulo/menor que zero.");
		
		return repository.buscar(id);
	}
	
	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		validacoes.aplicar(categoria);
		repository.atualizar(categoria);
	}
}
