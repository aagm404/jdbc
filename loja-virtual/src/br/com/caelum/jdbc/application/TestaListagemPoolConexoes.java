package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.db.ConnectionPool;

public class TestaListagemPoolConexoes {

	public static void main(String[] args) throws SQLException {

		// A vantagem do Pool de Conexoes e' explicada pelo "for" abaixo.
		// Sem o pool de conexoes, 100 conexoes seriam abertas e fechadas para executar o programa.
		// Com o pool de conexao, apenas um numero inicial de conexoes sao abertas. Quando se fecha a conexao
		// no final da execucao do for, a conexao volta para o pool de conexoes, nao sendo necessario abrir
		// mais conexoes dos que as que foram abertas inicialmente
		ConnectionPool pool = new ConnectionPool();
		
		for (int i = 0; i < 100; i++) {
			Connection connection = pool.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("select * from Produto;");
			ResultSet resultSet = null;

			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String descricao = resultSet.getString("descricao");
				System.out.println("[ID: " + id + ", NOME: " + nome + ", DESCRICAO: " + descricao + "]");
			}

			resultSet.close();
			statement.close();
		}
	}
}