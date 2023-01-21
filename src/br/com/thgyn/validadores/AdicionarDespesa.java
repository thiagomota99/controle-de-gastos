package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;

public class AdicionarDespesa implements Validador<Despesa> {
	
	@Override
	public void aplicar(Despesa t) {
		Objeto.isNotNull(t);
		Objeto.isNull(t.getId());
		Objeto.isNotNull(t.getValor());
		Objeto.isNotNull(t.getData());
		Objeto.isNotNull(t.getFormaDePagamento());
		Objeto.isNotLessOrEqualZero(t.getValor());
		
		if(t.getDescricao().trim().isEmpty())
			throw new IllegalArgumentException("Descrição não pode ser vazio.");
	}
}
