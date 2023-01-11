package br.com.thgyn.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.exceptions.CategoriaNaoEncontradaException;
import br.com.thgyn.modelo.entidades.Categoria;

public class CategoriaDAO implements Persistivel<Categoria> {
	
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private static Integer ID = 0;
	
	@Override
	public void adicionar(Categoria obj) {
		CategoriaDAO.ID = CategoriaDAO.ID + 1;
		this.categorias.add(new Categoria(CategoriaDAO.ID, obj.getNome()));
	}

	@Override
	public List<Categoria> listar() {
		return new ArrayList<Categoria>(this.categorias);
	}

	@Override
	public Categoria buscar(Integer id) {
		Categoria obj = null;
		for (Categoria categoria : this.categorias) {
			if(categoria.getId().intValue() == id.intValue()) {
				obj = categoria;
				break;
			}
		}	
		if(obj == null)
			throw new CategoriaNaoEncontradaException("Id não encontrado.");
		
		return new Categoria(obj.getId(), obj.getNome());
	}

	@Override
	public void atualizar(Categoria obj) {
		String nome = obj.getNome();
		this.categorias.get(obj.getId()).setNome(nome);
	}

	@Override
	public void deletar(Integer id) {
		this.categorias.remove(id);
	}
}
