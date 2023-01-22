package br.com.thgyn.dao;

import java.util.List;

import br.com.thgyn.modelo.entidades.Categoria;

public interface CategoriaDAO extends Persistivel<Categoria> {
	
	public void adicionar(Categoria obj);

	public List<Categoria> listar();

	public Categoria buscar(Integer id);

	public void atualizar(Categoria obj);

	public void deletar(Integer id);
}
