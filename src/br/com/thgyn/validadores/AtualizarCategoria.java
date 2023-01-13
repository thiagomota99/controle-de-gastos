package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class AtualizarCategoria implements Validador<Categoria>{

	@Override
	public void aplicar(Categoria t) {
		if(t == null)
			throw new IllegalArgumentException("O objeto categoria é nulo");
	}
	
}
