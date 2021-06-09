package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

/**
 * 
 * Classe utilizada para abstracao de camadas. Esta classe contem a logica de
 * acesso ao banco de dados. E' atraves desta classe que fazemos consultas
 * (SELECT), salvamos (INSERT), removemos (REMOVE) ou atualizamos (UPDATE)
 * produtos na tabela de Produto
 * 
 * Na epoca em que foi implementada, esta classe continha, apenas, os metodos
 * "salva" (e uma sobrecarga deste metodo), "lista()" e "busca()"
 */

public class ProdutoDAO {

	private Connection con;

	public ProdutoDAO(Connection con) {
		super();
		this.con = con;
	}

	// Metodo que contem a logica para salvar um produto no bd
	public void salva(Produto produto) {
		String query = "insert into produto (nome, descricao, categoria_id) values (?,?,?);";

		try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.setInt(3, produto.getCategoriaId());

			int rowCount = ps.executeUpdate();

			try (ResultSet generatedKey = ps.getGeneratedKeys()) {
				if (generatedKey.next()) {
					Integer id = generatedKey.getInt("GENERATED_KEY");
					produto.setId(id);
					System.out.println(produto);
					System.out.println(rowCount + " row(s) affected");
					System.out.println();
				} else {
					throw new SQLException();
				}
			}

		} catch (SQLException e) {
			System.out
					.println("Nao foi possivel salver este produto: " + "{ \"nome\": \"" + produto.getNome() + "\" }");
			e.getMessage();
			e.printStackTrace();
		}
	}

	// Sobrecarga do metodo "salva", que aceita uma lista de produtos para salvar no
	// bd
	public void salva(List<Produto> produtos) {

		String query = "insert into produto (nome, descricao, categoria_id) values (?,?,?);";

		try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			final StringBuilder rowsAffected = new StringBuilder("0");

			produtos.forEach(produto -> {
				try {
					ps.setString(1, produto.getNome());
					ps.setString(2, produto.getDescricao());
					ps.setInt(3, produto.getCategoriaId());

					int rowCount = ps.executeUpdate();

					rowCount = rowCount + Integer.valueOf(rowsAffected.toString());
					rowsAffected.delete(0, rowsAffected.length());
					rowsAffected.append(rowCount);

					try (ResultSet generatedKey = ps.getGeneratedKeys()) {

						while (generatedKey.next()) {
							Integer id = generatedKey.getInt("GENERATED_KEY");
							produto.setId(id);
							System.out.println(produto);
						}

					} catch (SQLException e) {
						System.out.println("Falha com o ResultSet");
						e.getMessage();
						e.printStackTrace();
					}

				} catch (SQLException e) {
					System.out.println("Falha com o PreparedStatement");
					e.getMessage();
					e.printStackTrace();
				}
			});
			System.out.println(rowsAffected + " row(s) affected");
			System.out.println();

		} catch (Exception e) {
			System.out.println("Falha ao criar PreparedStatement");
			e.getMessage();
			e.printStackTrace();
		}
	}

	// Metodo que faz uma consulta no bd e retorna todos os produtos armazenados
	public List<Produto> lista() {
		List<Produto> produtos = new ArrayList<>();

		String query = "select * from produto;";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			boolean hasProductList = ps.execute();

			if (hasProductList) {
				try (ResultSet result = ps.getResultSet()) {
					while (result.next()) {
						Produto produto = new Produto();
						produto.setId(result.getInt("id"));
						produto.setNome(result.getString("nome"));
						produto.setDescricao(result.getString("descricao"));

						produtos.add(produto);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Falha ao consultar os produtos");
			e.getMessage();
			e.printStackTrace();
		}

		return produtos;
	}
	
	public List<Produto> busca(Categoria categoria) {
		System.out.println("Executando uma query");
		List<Produto> produtos = new ArrayList<>();
		
		String query = "select * from produto where categoria_id = ?;";
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, categoria.getId());
			
			boolean hasProductList = ps.execute();
			if (hasProductList) {
				try (ResultSet result = ps.getResultSet()) {
					while (result.next()) {
						Produto produto = new Produto();
						produto.setId(result.getInt("id"));
						produto.setNome(result.getString("nome"));
						produto.setDescricao(result.getString("descricao"));

						produtos.add(produto);
					}
				}
			}
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return produtos;
	}
}
