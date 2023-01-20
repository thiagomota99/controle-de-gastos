package br.com.thgyn.validadores;

import br.com.thgyn.enums.AceitaNulo;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Validador;

public class AdicaoCategoria implements Validador<Categoria>{
	
	@Override
	public void aplicar(Categoria t) {
		ValidarReferencia.verify(t, AceitaNulo.NAO);
		ValidarReferencia.verify(t.getNome(), AceitaNulo.NAO);
		ValidarReferencia.verify(t.getId(), AceitaNulo.SIM);
		if(t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("A categoria precisa de um nome.");
	}
}
