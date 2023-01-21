package br.com.thgyn.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.exceptions.EntityNotFoundException;
import br.com.thgyn.modelo.entidades.Despesa;

public class DespesaDAO implements Persistivel<Despesa> {

	private static List<Despesa> despesas = new ArrayList<Despesa>();
	private static Integer ID = 0;

	@Override
	public void adicionar(Despesa obj) {
		DespesaDAO.ID = DespesaDAO.ID + 1;
		DespesaDAO.despesas.add(new Despesa(DespesaDAO.ID, obj.getValor(), obj.getFormaDePagamento(), obj.getData(),
				obj.getCategoria(), obj.getDescricao()));
	}

	@Override
	public List<Despesa> listar() {
		return new ArrayList<Despesa>(DespesaDAO.despesas);
	}

	@Override
	public Despesa buscar(Integer id) {
		Despesa obj = null;
		for (Despesa despesa : DespesaDAO.despesas) {
			if(despesa.getId().intValue() == id.intValue()) {
				obj = despesa;
				break;
			}
		}	
		if(obj == null)
			throw new EntityNotFoundException("Id não encontrado.");
		
		return new Despesa(obj.getId(), obj.getValor(), obj.getFormaDePagamento(), obj.getData(),
				obj.getCategoria(), obj.getDescricao());
	}

	@Override
	public void atualizar(Despesa obj) {
		
	}

	@Override
	public void deletar(Integer id) {

	}

}
