package br.com.thgyn.validadores;

import br.com.thgyn.exceptions.DespesaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Despesa;

public class AdicionarDespesa implements Validador<Despesa> {
	
	public AdicionarDespesa() {
		
	}
	
	@Override
	public void aplicar(Despesa t) {
		EntidadeException despesaException = new DespesaException("Erro ao adicionar despesa.");
		
		if(t == null) {
			despesaException.addErro("Objeto despesa n�o pode ser nulo.");
			throw despesaException;
		}
		if(t.getId() != null)
			despesaException.addErro("Id deve ser nulo.");
		if(t.getDescricao() == null || t.getDescricao().trim().isEmpty())
			despesaException.addErro("Descria��o n�o pode ser nulo/vazio.");
		if(t.getValor() == null || t.getValor().doubleValue() <= 0)
			despesaException.addErro("Valor n�o pode ser nulo/menor ou igual a zero.");
		if(t.getData() == null)
			despesaException.addErro("Data n�o pode ser nulo.");
		if(t.getFormaDePagamento() == null)
			despesaException.addErro("Forma de pagamento n�o pode ser nulo.");
		if(t.getCategoria() == null)
			despesaException.addErro("Categoria n�o pode ser nulo.");
			
		if(despesaException.getErros().size() > 0)
			throw despesaException;
	}
}
