package br.com.thgyn.modelo.services;

import java.util.List;

import br.com.thgyn.dao.Persistivel;
import br.com.thgyn.enums.AceitaNulo;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;
import br.com.thgyn.validadores.ValidarReferencia;

public class CategoriaService {
	
	Persistivel<Categoria> repository;
	
	public CategoriaService(Persistivel<Categoria> repository) {
		ValidarReferencia.verify(repository, AceitaNulo.NAO);
		this.repository = repository;
	}
	
	public void adicionar(Categoria obj, Validador<Categoria> validacoes) {
		ValidarReferencia.verify(validacoes, AceitaNulo.NAO);
		validacoes.aplicar(obj);
		repository.adicionar(obj);
	}
	
	public List<Categoria> listar(){
		return repository.listar();
	}
	
	public Categoria buscar(Integer id) {
		ValidarReferencia.verify(id, AceitaNulo.NAO);
		if(id <= 0)
			throw new IllegalArgumentException("O objeto: Id é nulo/menor que zero.");
		return repository.buscar(id);
	}
	
	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		ValidarReferencia.verify(validacoes, AceitaNulo.NAO);
		validacoes.aplicar(categoria);
		repository.atualizar(categoria);
	}
	
	public void deletar(Integer id) {
		ValidarReferencia.verify(id, AceitaNulo.NAO);
		if(id <= 0)
			throw new IllegalArgumentException("O objeto: Id é nulo/menor que zero.");
		repository.deletar(id);
	}
}
