package br.com.caelum.jdbc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * Classe para devolver um pool de conexoes, atraves do DataSource. 
 * Melhor dizendo, esta classe cria o Pool de Conexoes (DataSource) no construtor e o 
 * metodo "getConnection()" devolve uma conexao do Pool de Conexoes
 * 
 * Para obter uma unica conexao, atraves do DriveManager, utilize a classe "Database"
 *
 */
public class ConnectionPool {
	
	private final DataSource dataSource;
	
	public ConnectionPool() {
		HikariDataSource pool = new HikariDataSource();
		pool.setJdbcUrl("jdbc:mysql://localhost:3306/loja_virtual?serverTimezone=UTC");
		pool.setUsername("developer");
		pool.setPassword("dev123");
		
		this.dataSource = pool;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
