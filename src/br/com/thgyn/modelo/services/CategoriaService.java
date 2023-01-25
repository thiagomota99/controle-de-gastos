package br.com.thgyn.modelo.services;

import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Validador;

public class CategoriaService implements ServiceCRUD<Categoria> {
	
	private CategoriaDAO repository;
	
	public CategoriaService(CategoriaDAO repository) {
		Objeto.isNotNull(repository);
		this.repository = repository;
	}
	
	public void adicionar(Categoria obj, Validador<Categoria> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(obj);
		repository.setConnection(DB.getConnection());
		repository.adicionar(obj);
		DB.closeConnection();
	}
	
	public List<Categoria> listar() {
		repository.setConnection(DB.getConnection());
		List<Categoria> categorias =  repository.listar();
		DB.closeConnection();
		return categorias;
	}
	
	public Categoria buscar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		repository.setConnection(DB.getConnection());
		Categoria categoria = repository.buscar(id);
		DB.closeConnection();
		return categoria;
	}
	
	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(categoria);
		repository.setConnection(DB.getConnection());
		repository.atualizar(categoria);
		DB.closeConnection();
	}
	
	public void deletar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		repository.setConnection(DB.getConnection());
		repository.deletar(id);
		DB.closeConnection();
	}
}
