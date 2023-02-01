package br.com.thgyn.validadores;

import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;

public class AdicionarDespesa implements Adicionavel<Despesa> {
	EntidadeException despesaException;
	
	public AdicionarDespesa(EntidadeException exception) {
		Objeto.notNullOrException(exception);
		this.despesaException = exception;
	}
	
	public void aplicar(Despesa t) {
		notNull(t, "Despesa");
		nullable(t.getId(), "Id");
		notEmpty(t.getDescricao(), "Descrição");
		notNull(t.getValor(), "Valor");
		isMenorOuIgualAZero(t.getValor(), "Valor");
		notNull(t.getData(), "Data");
		notNull(t.getFormaDePagamento(), "Forma de Pagamento");
		notNull(t.getCategoria(), "Categoria");
			
		if(despesaException.getErros().size() > 0)
			throw despesaException;
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
