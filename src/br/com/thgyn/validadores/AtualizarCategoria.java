package br.com.thgyn.validadores;

import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.modelo.entidades.Categoria;

public class AtualizarCategoria implements Validador<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		CategoriaException categoriaException = new CategoriaException("Erro ao atualizara a categoria.");
		if(t == null)
			categoriaException.addErro("Objeto categoria � nulo.");
		if(categoriaException.getErros().size() > 0)
			throw categoriaException;
		else if(t.getId() == null || t.getId() <= 0) {
			categoriaException.addErro("Id n�o pode ser nulo/menor ou igual a zero.");
		}
		if(t.getNome() == null || t.getNome().trim().isEmpty())
			categoriaException.addErro("Nome n�o pode ser nulo/vazio.");
		if(categoriaException.getErros().size() > 0)
			throw categoriaException;
	}	
}
