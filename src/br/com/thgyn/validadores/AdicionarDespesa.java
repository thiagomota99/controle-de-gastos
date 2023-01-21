package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;

public class AdicionarDespesa implements Validador<Despesa> {
	
	public AdicionarDespesa() {
		
	}
	
	@Override
	public void aplicar(Despesa t) {
		Objeto.isNotNull(t);
		Objeto.isNull(t.getId());
		Objeto.isNotNull(t.getDescricao());
		Objeto.isNotNull(t.getValor());
		Objeto.isNotLessOrEqualZero(t.getValor());
		Objeto.isNotNull(t.getData());
		Objeto.isNotNull(t.getFormaDePagamento());
		Objeto.isNotNull(t.getCategoria());
		
		
		if(t.getDescricao().trim().isEmpty())
			throw new IllegalArgumentException("Descrição não pode ser vazio.");
	}
}
