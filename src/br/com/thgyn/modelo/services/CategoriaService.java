package br.com.thgyn.modelo.services;

import java.sql.Connection;
import java.util.ArrayList;
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
		
		Connection connection = null;
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.adicionar(obj);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection(connection);
		}
	}
	
	public List<Categoria> listar() {
		Connection connection = null;
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			categorias =  repository.listar();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			DB.closeConnection(connection);
		}
		return categorias;
	}
	
	public Categoria buscar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		
		Connection connection = null;
		Categoria categoria = null;
		try {
			connection = DB.getConnection();
			repository.setConnection(DB.getConnection());
			categoria = repository.buscar(id);
		}
		finally {
			DB.closeConnection(connection);
		}
		return categoria;
	}
	
	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(categoria);
		
		Connection connection = null;
		try {
			connection = DB.getConnection();
			repository.setConnection(DB.getConnection());
			repository.atualizar(categoria);
		} finally {
			DB.closeConnection(connection);
		}		
	}
	
	public void deletar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		
		Connection connection = null;
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.deletar(id);
		}finally {
			DB.closeConnection(connection);
		}
	}
}
