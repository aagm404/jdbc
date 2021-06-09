package br.com.caelum.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Classe para devolver apenas uma conexao, atraves do DriverManager. 
 * 
 * Para obter um Pool de Conexoes e uma conexao com o mesmo, utilize a classe "ConnectionPool"
 *
 */
public class Database {
	
	public static Connection getConnection() throws SQLException {

		System.out.println("Abrindo conexao com o bando de dados");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/loja_virtual?user=developer&password=dev123&serverTimezone=UTC");
		
		// O codigo abaixo utiliza de uma sobrecarga do metodo "getConnection()", da classe "DriveManager". Funciona certinho, tambem
		//
		// return DriverManager.getConnection("jdbc:mysql://localhost:3306/loja_virtual?serverTimezone=UTC", "developer", "dev123");
	}
}
