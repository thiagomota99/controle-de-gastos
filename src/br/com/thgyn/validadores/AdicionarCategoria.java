package br.com.thgyn.validadores;

import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.modelo.entidades.Categoria;

public class AdicionarCategoria implements Validador<Categoria>{
	
	@Override
	public void aplicar(Categoria t) {
		CategoriaException categoriaException = new CategoriaException("Erro ao adicionar a categoria.");
		if(t == null)
			categoriaException.addErro("Objeto categoria não pode ser nulo.");
		if(categoriaException.getErros().size() > 0)
			throw categoriaException;
		else if(t.getId() != null)
			categoriaException.addErro("O id deve ser nulo.");
		if(t.getNome() == null || t.getNome().trim().isEmpty())
			categoriaException.addErro("Nome não pode ser nulo/vazio.");
		if(categoriaException.getErros().size() > 0)
			throw categoriaException;
	}
}
