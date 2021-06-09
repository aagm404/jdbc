package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.CategoriaDAO;
import br.com.caelum.jdbc.db.ConnectionPool;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaCategorias {

	public static void main(String[] args) {
		try (Connection con = new ConnectionPool().getConnection()) {
			CategoriaDAO dao = new CategoriaDAO(con);
			
			List<Categoria> categorias = dao.listaComProdutos();
			categorias.forEach(categoria -> {
				System.out.println(categoria);
				List<Produto> produtos = categoria.getProdutos();
				produtos.forEach(produto -> {
					System.out.println(categoria.getNome() + " - " + produto.getNome());
				});
				System.out.println();
			});
			
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}
