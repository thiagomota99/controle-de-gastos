package br.com.thgyn.modelo.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.DespesaDAO;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.exceptions.DespesaException;
import br.com.thgyn.exceptions.EntityNotFoundException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Adicionavel;
import br.com.thgyn.validadores.Validador;

public class DespesaService implements ServiceCRUD<Despesa> {
	
	private DespesaDAO repository;
	private ServiceCRUD<Categoria> categoriaService;
	
	public DespesaService(DespesaDAO repository, ServiceCRUD<Categoria> categoriaService) {
		Objeto.notNullOrException(repository);
		Objeto.notNullOrException(categoriaService);
		
		this.categoriaService = categoriaService;
		this.repository = repository;
	}
	
	public void adicionar(Despesa despesa, Adicionavel<Despesa> validacoes) {
		Connection connection = null;
		try {
			Objeto.notNullOrException(validacoes);
			validacoes.aplicar(despesa);
			Objeto.notNullOrException(categoriaService.buscar(despesa.getCategoria().getId()));
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.adicionar(despesa);			
		} catch (NullPointerException | DbException e) {
			System.out.println(e.getMessage());
		} catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		} finally {
			DB.closeConnection(connection);
		}
	}

	@Override
	public List<Despesa> listar() {
		Connection connection = null;
		List<Despesa> despesas = new ArrayList<Despesa>();
		try {
			repository.setConnection(DB.getConnection());
			despesas = repository.listar();
		} catch (DbException | EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection(connection);			
		}
		return despesas;
	}

	@Override
	public Despesa buscar(Integer id) {
		Connection connection = null;
		Despesa despesa = null;
		try {
			Objeto.notNullOrException(id);
			Objeto.notLessEqualZeroOrException(id);
			connection = DB.getConnection();
			repository.setConnection(connection);
			despesa = repository.buscar(id);
		}
		catch (DbException | EntityNotFoundException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection(connection);
		}		
		return despesa;
	}

	@Override
	public void atualizar(Despesa despesa, Validador<Despesa> validacoes) {
		Connection connection = null;
		try {
			Objeto.notNullOrException(validacoes);
			validacoes.aplicar(despesa);
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.atualizar(despesa);
		} catch (DbException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		catch (DespesaException e) {
			e.getErros().forEach(erro -> System.out.println(erro));
		}
		finally {
			DB.closeConnection(connection);
		}		
	}

	@Override
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
		}
		finally {
			DB.closeConnection(connection);
		}
	}
}
