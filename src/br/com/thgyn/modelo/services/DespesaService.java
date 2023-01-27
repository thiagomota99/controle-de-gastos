package br.com.thgyn.modelo.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.DespesaDAO;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Validador;

public class DespesaService implements ServiceCRUD<Despesa> {
	
	private DespesaDAO repository;
	private ServiceCRUD<Categoria> categoriaService;
	
	public DespesaService(DespesaDAO repository, ServiceCRUD<Categoria> categoriaService) {
		Objeto.isNotNull(repository);
		Objeto.isNotNull(categoriaService);
		
		this.categoriaService = categoriaService;
		this.repository = repository;
	}
	
	public void adicionar(Despesa despesa, Validador<Despesa> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(despesa);
		
		Connection connection = null;
		try {
			categoriaService.buscar(despesa.getCategoria().getId());
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.adicionar(despesa);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection(connection);			
		}
		return despesas;
	}

	@Override
	public Despesa buscar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		
		Connection connection = null;
		Despesa despesa = null;
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			despesa = repository.buscar(id);
		}
		finally {
			DB.closeConnection(connection);
		}		
		return despesa;
	}

	@Override
	public void atualizar(Despesa despesa, Validador<Despesa> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(despesa);
		Connection connection = null;
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.atualizar(despesa);
		} catch (DbException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection(connection);
		}		
	}

	@Override
	public void deletar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		Connection connection = null;
		
		try {
			connection = DB.getConnection();
			repository.setConnection(connection);
			repository.deletar(id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection(connection);
		}
	}
}
