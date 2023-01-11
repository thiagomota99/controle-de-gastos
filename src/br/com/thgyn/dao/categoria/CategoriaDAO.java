package br.com.thgyn.dao.categoria;

import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.modelo.entidades.Categoria;

public class CategoriaDAO implements Persistivel<Categoria> {
	
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@Override
	public void adicionar(Categoria obj) {
		this.categorias.add(obj);
	}

	@Override
	public List<Categoria> listar() {
		return new ArrayList<Categoria>(this.categorias);
	}

	@Override
	public Categoria buscar(Integer id) {
		Categoria categoria = this.categorias.get(id);
		return new Categoria(categoria.getId(), categoria.getNome());
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
