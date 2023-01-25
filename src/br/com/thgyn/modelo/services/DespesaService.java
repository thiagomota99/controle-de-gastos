package br.com.thgyn.modelo.services;

import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.dao.DespesaDAO;
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
		categoriaService.buscar(despesa.getCategoria().getId());
		
		repository.setConnection(DB.getConnection());
		repository.adicionar(despesa);
		DB.closeConnection();
	}

	@Override
	public List<Despesa> listar() {
		repository.setConnection(DB.getConnection());
		List<Despesa> despesas = repository.listar();
		DB.closeConnection();
		return despesas;
	}

	@Override
	public Despesa buscar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		return repository.buscar(id);
	}

	@Override
	public void atualizar(Despesa despesa, Validador<Despesa> validacoes) {
		Objeto.isNotNull(validacoes);
		validacoes.aplicar(despesa);
		repository.atualizar(despesa);
	}

	@Override
	public void deletar(Integer id) {
		Objeto.isNotNull(id);
		Objeto.isNotLessOrEqualZero(id);
		repository.deletar(id);
	}
}
