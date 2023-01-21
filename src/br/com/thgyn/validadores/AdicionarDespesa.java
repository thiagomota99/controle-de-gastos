package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;

public class AdicionarDespesa implements Validador<Despesa> {
	
	private Validador<Categoria> validador;
	
	public AdicionarDespesa(Validador<Categoria> validador) {
		Objeto.isNotNull(validador);
		this.validador = validador;
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
