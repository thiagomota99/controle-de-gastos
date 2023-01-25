package br.com.thgyn.dao;

import java.sql.Connection;

import br.com.thgyn.modelo.entidades.Despesa;

public interface DespesaDAO extends Persistivel<Despesa> {
	
	public void setConnection(Connection connection);
}
