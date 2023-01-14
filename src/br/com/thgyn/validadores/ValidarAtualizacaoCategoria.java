package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class ValidarAtualizacaoCategoria implements Validador<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		if(t == null)
			throw new IllegalArgumentException("O objeto categoria não pode ser nulo.");
		if(t.getId() == null || t.getId() <= 0)
			throw new IllegalArgumentException("Id não pode ser nulo/menor ou igual a zero.");
		if(t.getNome() == null || t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("Nome não pode ser nulo/vazio.");
	}	
}
