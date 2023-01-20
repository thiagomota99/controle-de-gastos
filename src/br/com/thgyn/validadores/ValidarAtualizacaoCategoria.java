package br.com.thgyn.validadores;

import br.com.thgyn.enums.AceitaNulo;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class ValidarAtualizacaoCategoria implements Validador<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		ValidarReferencia.verify(t, AceitaNulo.NAO);
		ValidarReferencia.verify(t.getId(), AceitaNulo.NAO);
		ValidarReferencia.verify(t.getNome(), AceitaNulo.NAO);
		
		if(t.getId() <= 0)
			throw new IllegalArgumentException("Id n�o pode ser menor ou igual a zero.");
		if(t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("Nome n�o pode ser vazio.");
	}	
}
