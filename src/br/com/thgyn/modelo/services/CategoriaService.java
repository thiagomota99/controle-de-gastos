package br.com.thgyn.modelo.services;

import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.Persistivel;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Validador;

public class CategoriaService implements ServiceCRUD<Categoria> {
	
	Persistivel<Categoria> repository;
	
	public CategoriaService(Persistivel<Categoria> repository) {
		Objeto.isNotNull(repository);
		this.repository = repository;
	}
	
	public void adicionar(Categoria obj, Validador<Categoria> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(obj);
		repository.adicionar(obj);
		DB.closeConnection();
	}
	
	public List<Categoria> listar() {
		List<Categoria> categorias =  repository.listar();
		return categorias;
	}
	
	public Categoria buscar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		Categoria categoria = repository.buscar(id);
		return categoria;
	}
	
	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(categoria);
		repository.atualizar(categoria);
	}
	
	public void deletar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		repository.deletar(id);
	}
}
