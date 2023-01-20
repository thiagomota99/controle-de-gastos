package br.com.thgyn.validadores;

import br.com.thgyn.enums.AceitaNulo;

public class ValidarReferencia {
	
	public static void verify(Object ref, AceitaNulo aceita) {
		if(aceita.name() == AceitaNulo.SIM.name()) {
			if(ref != null)
				throw new IllegalArgumentException("Objeto da classe:" + ref.getClass().getSimpleName() + " � necess�rio ser nulo.");
		}
		else if (ref == null) {
			throw new NullPointerException("Objeto n�o pode ser nulo.");
		}
	}
}
