package br.com.thgyn.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.modelo.entidades.Despesa;

public class DespesaDAO implements Persistivel<Despesa> {

	private static List<Despesa> despesas = new ArrayList<Despesa>();
	private static Integer ID = 0;

	@Override
	public void adicionar(Despesa obj) {
		DespesaDAO.ID = DespesaDAO.ID + 1;
		DespesaDAO.despesas.add(new Despesa(DespesaDAO.ID, obj.getValor(), obj.getFormaDePagamento(), obj.getData(),
				obj.getCategoria()));
	}

	@Override
	public List<Despesa> listar() {
		return null;
	}

	@Override
	public Despesa buscar(Integer id) {
		return null;
	}

	@Override
	public void atualizar(Despesa obj) {

	}

	@Override
	public void deletar(Integer id) {

	}

}
