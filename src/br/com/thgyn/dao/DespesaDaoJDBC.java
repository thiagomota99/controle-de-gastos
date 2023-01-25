package br.com.thgyn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.modelo.entidades.Despesa;

public class DespesaDaoJDBC implements DespesaDAO {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	@Override
	public void adicionar(Despesa obj) {
		try {
			preparedStatement = connection.prepareStatement(""
					+ "INSERT INTO DESPESA "
					+ "(DESCRICAO, VALOR, DATA_CADASTRO, CATEGORIA_ID, FORMA_PAGAMENTO_ID) "
					+ "values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setDouble(2, obj.getValor());
			preparedStatement.setDate(3, new java.sql.Date(obj.getData().getTime()));
			preparedStatement.setInt(4, obj.getCategoria().getId());
			preparedStatement.setInt(5, obj.getFormaDePagamento().getCodigo());
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			if(linhasAfetadas == 0)
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Despesa> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Despesa buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Despesa obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
