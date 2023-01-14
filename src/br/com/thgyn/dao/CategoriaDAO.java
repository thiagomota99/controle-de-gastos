package br.com.thgyn.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.exceptions.CategoriaNaoEncontradaException;
import br.com.thgyn.modelo.entidades.Categoria;

public class CategoriaDAO implements Persistivel<Categoria> {
	
	private static List<Categoria> categorias = new ArrayList<Categoria>();
	private static Integer ID = 0;
	
	@Override
	public void adicionar(Categoria obj) {
		CategoriaDAO.ID = CategoriaDAO.ID + 1;
		CategoriaDAO.categorias.add(new Categoria(CategoriaDAO.ID, obj.getNome()));
	}

	@Override
	public List<Categoria> listar() {
		return new ArrayList<Categoria>(CategoriaDAO.categorias);
	}

	@Override
	public Categoria buscar(Integer id) {
		Categoria obj = null;
		for (Categoria categoria : CategoriaDAO.categorias) {
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
		CategoriaDAO.categorias.get(CategoriaDAO.categorias.indexOf(obj)).setNome(obj.getNome());
	}

	@Override
	public void deletar(Integer id) {
		CategoriaDAO.categorias.remove(id);
	}
}
