package br.com.thgyn.validadores.impl;

import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.validadores.Validador;

public class AtualizarCategoria implements Validador<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		EntidadeException categoriaException = new CategoriaException("Erro ao atualizara a categoria.");
		if(t == null) {
			categoriaException.addErro("Objeto categoria não pode ser nulo.");
			throw categoriaException;
		}
		if(t.getId() == null || t.getId() <= 0) {
			categoriaException.addErro("Id não pode ser nulo/menor ou igual a zero.");
		}
		if(t.getNome() == null || t.getNome().trim().isEmpty())
			categoriaException.addErro("Nome não pode ser nulo/vazio.");
		
		if(categoriaException.getErros().size() > 0)
			throw categoriaException;
	}	
}
