package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class CategoriaDAO {

	private Connection con;

	public CategoriaDAO(Connection con) {
		super();
		this.con = con;
	}

	// metodo que busca no bd todas as categoria armazenadas
	public List<Categoria> lista() {
		System.out.println("Executando uma query");
		List<Categoria> categorias = new ArrayList<>();

		String query = "select * from categoria;";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			boolean hasCategoryList = ps.execute();

			if (hasCategoryList) {
				try (ResultSet rs = ps.getResultSet()) {
					while (rs.next()) {
						Categoria categoria = new Categoria();
						Integer id = rs.getInt(1);
						String nome = rs.getString(2);
						categoria.setId(id);
						categoria.setNome(nome);
						categorias.add(categoria);
					}
				}
			}

		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return categorias;
	}

	// Metodo que busca no bd todas as categorias JOIN com os produtos
	public List<Categoria> listaComProdutos() {
		System.out.println("Executando uma query");
		List<Categoria> categorias = new ArrayList<>();
		
		Categoria ultima = null;

		String query = "select cat.id as c_id, cat.nome as c_nome, prod.id as p_id, prod.nome as p_nome, prod.descricao as p_descricao "
				+ "from categoria cat "
				+ "left join produto prod on prod.categoria_id = cat.id;";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			boolean hasCategoryList = ps.execute();

			if (hasCategoryList) {
				try (ResultSet rs = ps.getResultSet()) {
					while (rs.next()) {
						Integer id = rs.getInt("c_id");
						String nome = rs.getString("c_nome");
						
						if (ultima == null || !ultima.getNome().equals(nome)) {
							Categoria cat = new Categoria();
							cat.setId(id);
							cat.setNome(nome);
							categorias.add(cat);
							ultima = cat;
						}
						
						Produto prod = new Produto(); 
						prod.setId(rs.getInt("p_id"));
						prod.setNome(rs.getString("p_nome"));
						prod.setDescricao(rs.getString("p_descricao"));
						ultima.adiciona(prod);
						
					}
				}
			}

		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return categorias;
	}
}
