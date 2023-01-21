package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;

public class AtualizarCategoria implements Validador<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		Objeto.isNotNull(t);
		Objeto.isNotNull(t.getId());
		Objeto.isNotNull(t.getNome());
		Objeto.isNotLessOrEqualZero(t.getId());
		
		if(t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("Descrição não pode ser vazio.");
	}	
}
