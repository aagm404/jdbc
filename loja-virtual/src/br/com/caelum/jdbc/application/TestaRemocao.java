package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.db.ConnectionPool;

public class TestaRemocao {

	public static void main(String[] args) {

		try (Connection connection = new ConnectionPool().getConnection()) {

			try (Statement statement = connection.createStatement()) {
				
				statement.execute("delete from produto where id > 3;");
				int count = statement.getUpdateCount();
				
				System.out.println(count + " row(s) affected");
				
				statement.execute("alter table produto auto_increment = 1;");
				
			}

		} catch (SQLException e) {
			System.out.println("Falha ao abrir a conexao");
			e.getMessage();
			e.printStackTrace();
		}
	}
}
