package br.com.thgyn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.utils.Objeto;

public class CategoriaDaoJDBC implements CategoriaDAO {
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	@Override
	public void adicionar(Categoria obj) {
		try {
			preparedStatement = connection.prepareStatement(""
					+ "INSERT INTO CATEGORIA "
					+ "(DESCRICAO) "
					+ "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, obj.getNome());
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			if(linhasAfetadas == 0)
				throw new DbException("Erro! Nenhuma linha foi afetada. Objeto: " + obj);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Categoria> listar() {
		List<Categoria> lista = new ArrayList<Categoria>();
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM CATEGORIA");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
				lista.add(new Categoria(resultSet.getInt("ID"), resultSet.getString("DESCRICAO")));									
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		return lista;
	}

	@Override
	public Categoria buscar(Integer id) {
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM CATEGORIA WHERE ID = ?");
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
				return new Categoria(resultSet.getInt("ID"), resultSet.getString("DESCRICAO"));
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		return null;
	}

	@Override
	public void atualizar(Categoria obj) {
		try {
			preparedStatement = connection.prepareStatement("UPDATE CATEGORIA "
					+ "SET DESCRICAO = ? "
					+ "WHERE ID = ?");
			preparedStatement.setString(1, obj.getNome());
			preparedStatement.setInt(2, obj.getId());
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			if(linhasAfetadas == 0)
				throw new DbException("Erro! Nenhuma linha afetada. Objeto: " + obj);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public void deletar(Integer id) {
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM CATEGORIA WHERE ID = ?");
			preparedStatement.setInt(1, id);
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			if(linhasAfetadas == 0)
				throw new DbException("Erro! Nenhuma linha afetada. Id: " + id);			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public void setConnection(Connection connection) {
		if(Objeto.isNull(connection))
			throw new NullPointerException("Objeto connection não pode ser nulo.");
		this.connection = connection;
	}
}
