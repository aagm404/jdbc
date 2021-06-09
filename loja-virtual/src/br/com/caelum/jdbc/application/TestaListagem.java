package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.db.ConnectionPool;

public class TestaListagem {

	public static void main(String[] args) {
		try (Connection connection = new ConnectionPool().getConnection()){
			
			try (Statement statement = connection.createStatement()) {
				// O metodo "execute()" abaixo retorna um booleano
				// Retorna "true" se voltar do Banco de dados uma lista de dados
				// Retorna "false", caso contrario (mesmo que retorne o numero de linhas afetados)
				boolean resultado = statement.execute("select * from Produto;");
				
				if (resultado) {
					System.out.println("Retorno do metodo excute = true, entao o banco devolveu uma lista de dados");
					System.out.println();
					
					try (ResultSet resultSet = statement.getResultSet()) {
					
						while (resultSet.next()) {
							Integer id = resultSet.getInt("id");
							String nome = resultSet.getString("nome");
							String descricao = resultSet.getString("descricao");
							System.out.println("[ID: " + id + ", NOME: " + nome + ", DESCRICAO: " + descricao+ "]");
						}
					}
					
				} else {
					System.out.println("Retorno do metodo excute = false, entao o banco nao devolveu uma lista de dados");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao abrir a conexao");
			e.getMessage();
			e.printStackTrace();
		}
	}

}
