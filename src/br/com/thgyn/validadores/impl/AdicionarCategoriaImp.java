package br.com.thgyn.validadores.impl;

import br.com.thgyn.exceptions.CategoriaException;
import br.com.thgyn.exceptions.EntidadeException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;
import br.com.thgyn.validadores.Adicionavel;

public class AdicionarCategoriaImp implements Adicionavel<Categoria> {
	
	@Override
	public void aplicar(Categoria t) {
		EntidadeException categoriaException = new CategoriaException("Erro ao adicionar a categoria.");
		if(Objeto.isNull(t)) {
			categoriaException.addErro("Categoria não pode ser nulo.");
			throw categoriaException;
		}
		if(Objeto.isNotNull(t.getId()))
			categoriaException.addErro("O id deve ser nulo.");
		if(Objeto.isEmpty(t.getNome()))
			categoriaException.addErro("Nome não pode ser nulo/vazio.");
		
		if(categoriaException.getErros().size() > 0)
			throw categoriaException;
	}
}
