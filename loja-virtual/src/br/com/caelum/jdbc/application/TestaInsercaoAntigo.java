package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.db.ConnectionPool;

public class TestaInsercaoAntigo {
	
	public static void main(String[] args) {
		try (Connection connection = new ConnectionPool().getConnection()) {
			
			String sql = "insert into produto (nome, descricao, categoria_id) values (?, ?, ?);";
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, "Grampeador");
				// As aspas simples, na string abaixo, foram colocadas de proposito para verificar o scape automatico do PreparedStatement
				statement.setString(2, "Grampeador 'sem' grampo");
				statement.setInt(3, 4);
	
				statement.execute();
	
				
				// O metodo "getGeneratedKeys()" retorna uma tabela resultado do tipo objeto "ResultSet"
				// Esta tabela tem um numero de linhas igual ao numero de linhas inseridas na query "insert" acima
				// O nome desta coluna e' auto-intitulado como "GENERATE_KEY". Imagino que quem faz esta auto-intitulacao
				// seja a implementacao da api JDBC contida no driver do MySQL
				try (ResultSet generatedKey = statement.getGeneratedKeys()) {
				
					while (generatedKey.next()) {
						/*
						 **** Todos os codigos comentados abaixo sao validos para este codigo. Sao diferentes maneiras de se obter o mesmo dado
						 * 
						 **** Um inteiro passado com parametro nos metodos abaixo, significa que estamos buscando resultados pelo numero da coluna
						 **** No caso em questao, temos apenas uma coluna
						 * 
						 * String id = generatedKey.getString(1);
						 * Integer id = generatedKey.getInt(1);
						 *
						 **** Umas string passada como paramentro nos metodos abaixo, significa que estamos buscando resultados pelo NOME
						 **** da coluna. No caso do do metodo "getGeneratedKeys()" a aplicação intitula o nome da coluna de resultado
						 **** como "GENERATED_KEY"
						 * 
					     * String id = generatedKey.getString("GENERATED_KEY");
		                 */
		 
						Integer id = generatedKey.getInt("GENERATED_KEY");
						System.out.println("[ID: " + id + "]");
						System.out.println();
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao abrir a conexao");
			e.getMessage();
			e.printStackTrace();
		}
	}
}
