package br.com.thgyn.validadores;

import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;

public class AdicionarCategoria implements Validador<Categoria>{
	
	@Override
	public void aplicar(Categoria t) {
		Objeto.isNotNull(t);
		Objeto.isNull(t.getId());
		Objeto.isNotNull(t.getNome());
		
		if(t.getNome().trim().isEmpty())
			throw new IllegalArgumentException("Descrição não pode ser vazio.");
	}
}
