package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.services.CategoriaService;

public interface AdicionarDespesa {
	
	public void aplicar(Despesa t, CategoriaService serviceCategoria);
}
