package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.jdbc.db.ConnectionPool;

public class TestaConexao {

	public static void main(String[] args) {
		try (Connection connection = new ConnectionPool().getConnection()){
			System.out.println("Conexao aberta com sucesso!");
		} catch (SQLException e) {
			System.out.println("Falha ao abrir a conexao");
			e.getMessage();
			e.printStackTrace();
		}
	}
}