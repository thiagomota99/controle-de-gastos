package br.com.thgyn.validadores;

import br.com.thgyn.dao.CategoriaDAO;
import br.com.thgyn.enums.AceitaNulo;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.modelo.services.CategoriaService;
import br.com.thgyn.utils.Validador;

public class AdicaoDespesa implements Validador<Despesa> {
	
	@Override
	public void aplicar(Despesa t) {
		ValidarReferencia.verify(t, AceitaNulo.NAO);
		ValidarReferencia.verify(t.getId(), AceitaNulo.SIM);
		ValidarReferencia.verify(t.getValor(), AceitaNulo.NAO);
		ValidarReferencia.verify(t.getData(), AceitaNulo.NAO);
		ValidarReferencia.verify(t.getFormaDePagamento(), AceitaNulo.NAO);

		if(t.getValor() > 0)
			throw new IllegalArgumentException("O valor da despesa deve ser maior que zero.");
		
		new VerificarCategoria(new CategoriaService(new CategoriaDAO())).aplicar(t.getCategoria());
	}

}
