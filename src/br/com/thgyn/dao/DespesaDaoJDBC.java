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
import br.com.thgyn.modelo.entidades.Categoria;
import br.com.thgyn.modelo.entidades.Despesa;

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
		try {
			StringBuilder sql = listarDespeasSQL();
			preparedStatement = connection.prepareStatement(sql.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<Despesa> despesas = new ArrayList<Despesa>();
			while(resultSet.next()) 
				despesas.add(criarObejtoDespesa(resultSet));
			
			return despesas;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
			System.out.println("Map de Categorias: " + categorias.size());
			System.out.println("Map de Formas De Pagamento: " + formasDePagamento.size());
		}
	}
	
	private Categoria getCategoria(ResultSet resultSet) throws SQLException {
		Integer key = resultSet.getInt("C.ID");
		if(categorias.get(key) == null) {
			Categoria temp = criarObjetoCategotira(resultSet);
			categorias.put(temp.getId(), temp);
			return categorias.get(key);
		}
		else {
			return categorias.get(key);
		}
	}

	private Despesa criarObejtoDespesa(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("D.ID");
		Double valor = resultSet.getDouble("D.VALOR");
		FormaDePagamento fp = getFormaPagamento(resultSet);
		Date data = resultSet.getDate("D.DATA_CADASTRO");
		Categoria categoria = getCategoria(resultSet);
		String descricao = resultSet.getString("D.DESCRICAO");
		
		Despesa despesa = new Despesa(id, valor, fp, data, categoria, descricao);		
		return despesa;
	}
	
	private FormaDePagamento getFormaPagamento(ResultSet resultSet) throws SQLException {
		Integer key = resultSet.getInt("FP.ID");
		if(formasDePagamento.get(key) == null) {
			FormaDePagamento temp = criarEnumFormaPagamento(resultSet);
			formasDePagamento.put(temp.getCodigo(), temp);
			return formasDePagamento.get(key);
		}
		else {
			return formasDePagamento.get(key);
		}
	}
	
	private Categoria criarObjetoCategotira(ResultSet resultSet) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setId(resultSet.getInt("C.ID"));
		categoria.setNome(resultSet.getString("C.DESCRICAO"));
		return categoria;
	}
	
	private FormaDePagamento criarEnumFormaPagamento(ResultSet resultSet) throws SQLException {
		FormaDePagamento fp = FormaDePagamento.valueOf(resultSet.getInt("FP.ID"));
		return fp;
	}

	private StringBuilder listarDespeasSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("D.ID, D.DESCRICAO, D.VALOR, D.DATA_CADASTRO, ");
		sql.append("C.ID, C.DESCRICAO, ");
		sql.append("FP.ID ");
		sql.append("FROM DESPESA AS D ");
		sql.append("INNER JOIN CATEGORIA AS C ON (C.ID = D.CATEGORIA_ID) ");
		sql.append("INNER JOIN FORMA_PAGAMENTO AS FP ON (FP.ID = D.FORMA_PAGAMENTO_ID) ");
		sql.append("ORDER BY DATA_CADASTRO");
		return sql;
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
