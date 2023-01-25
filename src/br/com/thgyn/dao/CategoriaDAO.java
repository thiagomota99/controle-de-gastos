package br.com.thgyn.dao;

import java.sql.Connection;

import br.com.thgyn.modelo.entidades.Categoria;

public interface CategoriaDAO extends Persistivel<Categoria> {
	
	public void setConnection(Connection connection);
}
