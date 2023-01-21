package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;

public class AtualizarDespesa implements Validador<Despesa> {
	
	private Validador<Categoria> validador;
	
	public AtualizarDespesa(Validador<Categoria> validador) {
		Objeto.isNotNull(validador);
		this.validador = validador;
	}

	@Override
	public void aplicar(Despesa t) {
		Objeto.isNotNull(t);
		Objeto.isNotNull(t.getId());
		Objeto.isNotNull(t.getValor());
		Objeto.isNotNull(t.getData());
		Objeto.isNotNull(t.getFormaDePagamento());
		Objeto.isNotLessOrEqualZero(t.getValor());
		
		if(t.getDescricao().trim().isEmpty())
			throw new IllegalArgumentException("Descrição não pode ser vazio.");
		
		this.validador.aplicar(t.getCategoria());		
	}
}
