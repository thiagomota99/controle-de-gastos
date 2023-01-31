package br.com.thgyn.modelo.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.exceptions.EntityNotFoundException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Validador;

public class CategoriaService implements ServiceCRUD<Categoria> {

	private CategoriaDAO repository;

	public CategoriaService(CategoriaDAO repository) {
		Objeto.notNullOrException(repository);
		this.repository = repository;
	}

	public void adicionar(Categoria obj, Validador<Categoria> validacoes) {
		Connection connection = null;
		try {
			Objeto.notNullOrException(validacoes);
			validacoes.aplicar(obj);
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.adicionar(obj);
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (DbException | NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.closeConnection(connection);
		}
	}

	public List<Categoria> listar() {
		Connection connection = null;
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			categorias = repository.listar();
		} catch (DbException | EntityNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.closeConnection(connection);
		}
		return categorias;
	}

	public Categoria buscar(Integer id) {
		Connection connection = null;
		Categoria categoria = null;
		try {
			Objeto.notNullOrException(id);
			Objeto.notLessEqualZeroOrException(id);
			connection = DB.getConnection();
			repository.setConnection(DB.getConnection());
			categoria = repository.buscar(id);
		} catch (DbException | EntityNotFoundException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.closeConnection(connection);
		}
		return categoria;
	}

	public void atualizar(Categoria categoria, Validador<Categoria> validacoes) {
		Connection connection = null;
		try {
			Objeto.notNullOrException(validacoes);
			validacoes.aplicar(categoria);
			connection = DB.getConnection();
			repository.setConnection(DB.getConnection());
			repository.atualizar(categoria);
		} catch (CategoriaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} catch (NullPointerException | DbException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.closeConnection(connection);
		}
	}

	public void deletar(Integer id) {
		Connection connection = null;
		try {
			Objeto.notNullOrException(id);
			Objeto.notLessEqualZeroOrException(id);
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.deletar(id);
		} catch (NullPointerException | IllegalArgumentException | DbException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.closeConnection(connection);
		}
	}
}
