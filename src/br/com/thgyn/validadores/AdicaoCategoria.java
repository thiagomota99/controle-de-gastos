package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class AdicaoCategoria implements Validador<Categoria>{

	@Override
	public void aplicar(Categoria t) {
		if(t == null)
			throw new IllegalArgumentException("O objeto categoria é nulo");
		if(t.getId() != null)
			throw new IllegalArgumentException("O identificador de novas categorias deve ser nulo");
		if(t.getNome() == null || t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("A categoria precisa de um nome.");
	}
}
