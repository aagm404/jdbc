package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.db.ConnectionPool;

public class TestaInsercao {

	public static void main(String[] args) {

		try (Connection connection = new ConnectionPool().getConnection()) {
			connection.setAutoCommit(false);
			
			String sql = "insert into produto (nome, descricao, categoria_id) values (?, ?, ?);";
			
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	
				adiciona("Teclado", "Teclado sem teclas", 2, statement);
				adiciona("TV LCD", "32 polegadas", 2, statement);
				adiciona("Blueray", "Full HDMI", 2, statement);
				
				connection.commit();
				
			} catch (Exception e) {
				connection.rollback();
				e.getMessage();
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao abrir a conexao");
			e.getMessage();
			e.printStackTrace();
		}
	}

	private static void adiciona(String nome, String descricao, Integer categoria_id, PreparedStatement statement) throws SQLException {

		// Trecho para testar erros em situacoes de autocommit nao habilitado
//		if (nome.equals("Blueray")) {
//			throw new IllegalArgumentException("Problema ocorrido");
//		}

		statement.setString(1, nome);
		statement.setString(2, descricao);
		statement.setInt(3, categoria_id);

		boolean resultado = statement.execute();
		System.out.println(resultado);

		ResultSet generatedKey = statement.getGeneratedKeys();

		while (generatedKey.next()) {
			Integer id = generatedKey.getInt("GENERATED_KEY");
			System.out.println("[ID: " + id + "] gerado");
			System.out.println();
		}
		generatedKey.close();
	}
}
