package br.com.thgyn.modelo.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.DespesaDAO;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.exceptions.DespesaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.services.CategoriaService;
import br.com.thgyn.services.DespesaService;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.AdicionarDespesa;
import br.com.thgyn.validadores.Validador;

public class DespesaServiceImpl implements DespesaService {
	
	private DespesaDAO repository;
	private CategoriaService categoriaService;
	private EntidadeException despesaException = new DespesaException(null);
	
	private final String MSG_ERRO = "Houve um erro. Por gentileza, tente novamente ou informe o administrador do sistema.";
	
	public DespesaServiceImpl(DespesaDAO repository, CategoriaService categoriaService) {
		Objeto.notNullOrException(repository);
		Objeto.notNullOrException(categoriaService);
		
		this.categoriaService = categoriaService;
		this.repository = repository;
	}
	
	@Override
	public void adicionar(Despesa obj, AdicionarDespesa validacoes) {
		Connection connection = null;
		Objeto.notNullOrException(validacoes);
		validacoes.aplicar(obj, categoriaService);
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.adicionar(obj);			
		} catch (DbException e) {
			System.out.println(e.getMessage());
			despesaException.setStackTrace(e.getStackTrace());
			despesaException.addErro(MSG_ERRO);
			throw despesaException;
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
		} catch (DbException e) {
			System.out.println(e.getMessage());
			despesaException.setStackTrace(e.getStackTrace());
			despesaException.addErro(MSG_ERRO);
			throw despesaException;
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
		Objeto.notNullOrException(id);
		Objeto.notLessEqualZeroOrException(id);		
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			despesa = repository.buscar(id);
		}
		catch (DbException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			despesaException.setStackTrace(e.getStackTrace());
			despesaException.addErro(MSG_ERRO);
			throw despesaException;
		}
		finally {
			DB.closeConnection(connection);
		}		
		return despesa;
	}

	@Override
	public void atualizar(Despesa despesa, Validador<Despesa> validacoes) {
		Connection connection = null;
		Objeto.notNullOrException(validacoes);
		validacoes.aplicar(despesa);
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.atualizar(despesa);
		} catch (DbException e) {
			System.out.println(e.getMessage());
			despesaException.setStackTrace(e.getStackTrace());
			despesaException.addErro(MSG_ERRO);
			throw despesaException;
		} finally {
			DB.closeConnection(connection);
		}		
	}

	@Override
	public void deletar(Integer id) {
		Connection connection = null;
		Objeto.notNullOrException(id);
		Objeto.notLessEqualZeroOrException(id);
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.deletar(id);
		} catch (DbException e) {
			System.out.println(e.getMessage());
			despesaException.setStackTrace(e.getStackTrace());
			despesaException.addErro(MSG_ERRO);
			throw despesaException;
		} finally {
			DB.closeConnection(connection);
		}
	}
}
