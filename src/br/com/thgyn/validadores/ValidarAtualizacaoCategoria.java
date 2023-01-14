package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class ValidarAtualizacaoCategoria implements Validador<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		if(t == null)
			throw new IllegalArgumentException("O objeto categoria n�o pode ser nulo.");
		if(t.getId() == null || t.getId() <= 0)
			throw new IllegalArgumentException("Id n�o pode ser nulo/menor ou igual a zero.");
		if(t.getNome() == null || t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("Nome n�o pode ser nulo/vazio.");
	}	
}
