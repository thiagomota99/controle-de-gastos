package br.com.thgyn.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.thgyn.conexao.DB;
import br.com.thgyn.enums.FormaDePagamento;
import br.com.thgyn.exceptions.DbException;
import br.com.thgyn.exceptions.EntityNotFoundException;
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;
import br.com.thgyn.utils.Objeto;

public class DespesaDaoJDBC implements DespesaDAO {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	private Map<Integer,Categoria> categorias = new HashMap<Integer,Categoria>();
	private Map<Integer,FormaDePagamento> formasDePagamento = new HashMap<Integer,FormaDePagamento>();
	
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
				throw new DbException("Erro! Nenhuma linha foi afetada!");
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
		List<Despesa> despesas = new ArrayList<Despesa>();
		try {
			preparedStatement = connection.prepareStatement(listarDespeasSQL());
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
				do 
					despesas.add(criarObejtoDespesa());
				while (resultSet.next());
			else
				throw new EntityNotFoundException("Não existe nenhuma despesa cadastrada.");			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		return despesas;
	}
	
	private String listarDespeasSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("D.ID, D.DESCRICAO, D.VALOR, D.DATA_CADASTRO, ");
		sql.append("C.ID, C.DESCRICAO, ");
		sql.append("FP.ID ");
		sql.append("FROM DESPESA AS D ");
		sql.append("INNER JOIN CATEGORIA AS C ON (C.ID = D.CATEGORIA_ID) ");
		sql.append("INNER JOIN FORMA_PAGAMENTO AS FP ON (FP.ID = D.FORMA_PAGAMENTO_ID) ");
		sql.append("ORDER BY DATA_CADASTRO");
		return sql.toString();
	}	
	
	@Override
	public Despesa buscar(Integer id) {
		Despesa despesa = null;
		try {
			preparedStatement = connection.prepareStatement(buscarDespesa());
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
				despesa = criarObejtoDespesa();
			else
				throw new EntityNotFoundException("Nenhuma despesa cadastrada com esse id.");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		return despesa;
	}
	
	private String buscarDespesa() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("D.ID, D.DESCRICAO, D.VALOR, D.DATA_CADASTRO, ");
		sql.append("C.ID, C.DESCRICAO, ");
		sql.append("FP.ID ");
		sql.append("FROM DESPESA AS D ");
		sql.append("INNER JOIN CATEGORIA AS C ON (C.ID = D.CATEGORIA_ID) ");
		sql.append("INNER JOIN FORMA_PAGAMENTO AS FP ON (FP.ID = D.FORMA_PAGAMENTO_ID) ");
		sql.append("WHERE D.ID = ?");
		return sql.toString();
	}	

	@Override
	public void atualizar(Despesa obj) {
		try {
			preparedStatement = connection.prepareStatement(atualizarDespesa());
			preparedStatement.setString(1, obj.getDescricao());
			preparedStatement.setDouble(2, obj.getValor());
			preparedStatement.setInt(3, obj.getId());
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			if(linhasAfetadas == 0)
				throw new DbException("Erro! Nenhuma linha afetada.");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private String atualizarDespesa() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE DESPESA SET DESCRICAO = ?, VALOR = ? WHERE ID = ?");		
		return sql.toString();
	}

	@Override
	public void deletar(Integer id) {
		try {
			preparedStatement = connection.prepareStatement(deletarDespesa());
			preparedStatement.setInt(1, id);
			
			int linhasAfetadas = preparedStatement.executeUpdate();
			if(linhasAfetadas == 0)
				throw new DbException("Erro! Nenhuma linha afetada.");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private String deletarDespesa() {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM DESPESA WHERE ID = ?");
		return sql.toString();
	}

	@Override
	public void setConnection(Connection connection) {
		Objeto.isNotNull(connection);
		this.connection = connection;
	}
	
	private Despesa criarObejtoDespesa() throws SQLException {
		Integer id = resultSet.getInt("D.ID");
		Double valor = resultSet.getDouble("D.VALOR");
		FormaDePagamento fp = getFormaPagamento();
		Date data = resultSet.getDate("D.DATA_CADASTRO");
		Categoria categoria = getCategoria(resultSet);
		String descricao = resultSet.getString("D.DESCRICAO");
		
		Despesa despesa = new Despesa(id, valor, fp, data, categoria, descricao);		
		return despesa;
	}
	
	private FormaDePagamento getFormaPagamento() throws SQLException {
		Integer key = resultSet.getInt("FP.ID");
		if(formasDePagamento.get(key) == null) {
			FormaDePagamento temp = criarEnumFormaPagamento();
			formasDePagamento.put(temp.getCodigo(), temp);
			return formasDePagamento.get(key);
		}
		else {
			return formasDePagamento.get(key);
		}
	}
	
	private FormaDePagamento criarEnumFormaPagamento() throws SQLException {
		FormaDePagamento fp = FormaDePagamento.valueOf(resultSet.getInt("FP.ID"));
		return fp;
	}
	
	private Categoria getCategoria(ResultSet resultSet) throws SQLException {
		Integer key = resultSet.getInt("C.ID");
		if(categorias.get(key) == null) {
			Categoria temp = criarObjetoCategotira();
			categorias.put(temp.getId(), temp);
			return categorias.get(key);
		}
		else {
			return categorias.get(key);
		}
	}	
	
	private Categoria criarObjetoCategotira() throws SQLException {
		Integer id = resultSet.getInt("C.ID");
		String nome = resultSet.getString("C.DESCRICAO");
		return new Categoria(id, nome);
	}
}
