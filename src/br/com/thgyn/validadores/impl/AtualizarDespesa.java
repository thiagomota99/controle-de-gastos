package br.com.thgyn.validadores.impl;

import br.com.thgyn.exceptions.DespesaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.validadores.Validador;

public class AtualizarDespesa implements Validador<Despesa> {

	@Override
	public void aplicar(Despesa t) {
		EntidadeException exception = new DespesaException("Erro ao atualizar a despesa.");
		if(t == null) {
			exception.addErro("Objeto despesa não pode ser nulo.");
			throw exception;
		}
		if(t.getId() == null || t.getId().intValue() <= 0)
			exception.addErro("Id não pode ser nulo/menor ou igual a zero.");
		if(t.getValor() == null || t.getValor().doubleValue() <= 0.0)
			exception.addErro("Valor não pode ser nulo/menor ou igual a zero.");
		if(t.getDescricao() == null || t.getDescricao().trim().isEmpty())
			exception.addErro("Descrição não pode ser nulo/vazio.");
		
		if(exception.getErros().size() > 0)
			throw exception;				
	}
}
