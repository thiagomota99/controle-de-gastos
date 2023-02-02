package br.com.thgyn.validadores.impl;

import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.services.CategoriaService;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.AdicionarDespesa;

public class AdicionarDespesaImpl implements AdicionarDespesa {
	
	private EntidadeException despesaException;
	
	public AdicionarDespesaImpl(EntidadeException exception) {
		Objeto.notNullOrException(exception);
		this.despesaException = exception;
	}
	
	@Override
	public void aplicar(Despesa t, CategoriaService serviceCategoria) {
		Objeto.notNullOrException(serviceCategoria);
		validarObjeto(t);
		
		if(Objeto.isNull(t.getCategoria().getId()) || t.getCategoria().getId() <= 0)
			despesaException.addErro("Objeto Id da categoria não pode ser nulo/menor ou igual a zero.");
		else if(Objeto.isNull(serviceCategoria.buscar(t.getCategoria().getId()))) {
			despesaException.addErro("A categoria informada não existe.");
		}
		
		if(despesaException.getErros().size() > 0)
			throw despesaException;
	}
	
	private void validarObjeto(Despesa t) {
		notNull(t, "Despesa");
		nullable(t.getId(), "Id");
		notEmpty(t.getDescricao(), "Descrição");
		notNull(t.getValor(), "Valor");
		isMenorOuIgualAZero(t.getValor(), "Valor");
		notNull(t.getData(), "Data");
		notNull(t.getFormaDePagamento(), "Forma de Pagamento");
		notNull(t.getCategoria(), "Categoria");
	}
	
	private boolean notNull(Object obj, String atributo) {
		Objeto.notNullOrException(atributo);
		if(Objeto.isNull(obj)) {
			String msg = "Objeto ".concat(atributo.concat(" não pode ser nulo."));
			despesaException.addErro(msg);
			return true;
		}
		return false;
	}
	
	private boolean nullable(Number obj, String atributo) {
		Objeto.notNullOrException(atributo);
		if(Objeto.isNotNull(obj)) {
			String msg = "Objeto ".concat(atributo.concat(" deve ser nulo."));
			despesaException.addErro(msg);
			return true;
		}
		return false;
	}
	
	private boolean notEmpty(String obj, String atributo) {
		Objeto.notNullOrException(atributo);
		if(Objeto.isEmpty(obj)) {
			String msg = "Objeto ".concat(atributo.concat(" não pode ser nulo/vazio."));
			despesaException.addErro(msg);
			return true;
		}
		return false;
	}
	
	private boolean isMenorOuIgualAZero(Double obj, String atributo) {
		Objeto.notNullOrException(atributo);
		Objeto.notNullOrException(obj);
		if(obj.doubleValue() <= 0) {
			String msg = "Objeto ".concat(atributo.concat(" não pode ser menor ou igual a zero."));
			despesaException.addErro(msg);
			return true;
		}
		return false;
	}
}
