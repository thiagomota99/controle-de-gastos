package br.com.thgyn.modelo.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.services.CategoriaService;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Adicionavel;
import br.com.thgyn.validadores.Validador;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO repository;
	private EntidadeException categoriaException;

	public CategoriaServiceImpl(CategoriaDAO repository) {
		Objeto.notNullOrException(repository);
		
		this.repository = repository;
		this.categoriaException = new CategoriaException("");
	}
	
	@Override
	public void adicionar(Categoria obj, Adicionavel<Categoria> validacoes) {
		Connection connection = null;
		Objeto.notNullOrException(validacoes);
		validacoes.aplicar(obj);
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.adicionar(obj);
		} catch (DbException e) {
			System.out.println(e.getMessage());
			categoriaException.setStackTrace(e.getStackTrace());
			categoriaException.addErro("Erro ao cadastrar a categoria. Por gentileza, tentar novamente.");
			throw categoriaException;
		} finally {
			DB.closeConnection(connection);
		}
	}
	
	@Override
	public List<Categoria> listar() {
		Connection connection = null;
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			categorias = repository.listar();
		} catch (DbException e) {
			System.out.println(e.getMessage());
			categoriaException.setStackTrace(e.getStackTrace());
			categoriaException.addErro("Erro ao listar as categorias. Por gentileza, tente novamente.");
			throw categoriaException;
		} finally {
			DB.closeConnection(connection);
		}
		return categorias;
	}
	
	@Override
	public Categoria buscar(Integer id) {
		Connection connection = null;
		Categoria categoria = null;
		if(Objeto.isNull(id) || id <= 0) {
			categoriaException.addErro("Id não pode ser nulo/menor ou igual a zero.");
			throw categoriaException;
		}
		
		try {
			connection = DB.getConnection();
			repository.setConnection(DB.getConnection());
			categoria = repository.buscar(id);
		} catch (DbException e) {
			System.out.println(e.getMessage());
			categoriaException.setStackTrace(e.getStackTrace());
			categoriaException.addErro("Erro ao buscar a categoria. Por gentileza, tentar novamente.");
			throw categoriaException;
		} finally {
			DB.closeConnection(connection);
		}
		return categoria;
	}
	
	@Override
	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		Connection connection = null;
		Objeto.notNullOrException(validacoes);
		validacoes.aplicar(categoria);
		try {
			connection = DB.getConnection();
			repository.setConnection(DB.getConnection());
			repository.atualizar(categoria);
		} catch (DbException e) {
			System.out.println(e.getMessage());
			categoriaException.setStackTrace(e.getStackTrace());
			categoriaException.addErro("Erro ao atualizar a categoria. Por gentileza, tente novamente.");
			throw categoriaException;
		} finally {
			DB.closeConnection(connection);
		}
	}

	@Override
	public void deletar(Integer id) {
		Connection connection = null;
		if(Objeto.isNull(id) || id <= 0) {
			categoriaException.addErro("Id não pode ser nulo/menor ou igual a zero.");
			throw categoriaException;
		}
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.deletar(id);
		} catch (DbException e) {
			System.out.println(e.getMessage());
			categoriaException.setStackTrace(e.getStackTrace());
			categoriaException.addErro("Erro ao deletar a categoria. Por gentileza, tente novamente.");
			throw categoriaException;
		} finally {
			DB.closeConnection(connection);
		}
	}
}
