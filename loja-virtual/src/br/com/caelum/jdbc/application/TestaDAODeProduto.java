package br.com.caelum.jdbc.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.db.ConnectionPool;
import br.com.caelum.jdbc.modelo.Produto;

/**
 * Esta classe testa a abstracao DAO de produtos
 */
public class TestaDAODeProduto {

	public static void main(String[] args) {
		
		Produto caderno = new Produto("Caderno", "Caderno sem folhas", 4);
		
		Produto mesa = new Produto("Mesa Azul", "Mesa com 4 pés", 5);
		Produto teclado = new Produto("Teclado", "Teclado sem teclas", 2);
		Produto tv = new Produto("Liquidificador", "Liquidificador para sucos", 1);
		Produto blueray = new Produto("Armário", "Armário sem portas", 5);
		List<Produto> produtos = Arrays.asList(mesa, teclado, tv, blueray);

		try (Connection con = new ConnectionPool().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(con);
			
			System.out.println("***** SALVANDO UM UNICO PRODUTO *****");
			dao.salva(caderno);
			
			System.out.println("***** SALVANDO UMA LISTA DE PRODUTOS *****");
			dao.salva(produtos);
			
			List<Produto> produtosArmazenados = dao.lista();
			System.out.println("***** LISTANDO PRODUTOS ARMAZENADOS NO BANCO DE DADOS *****");
			imprimeProdutos(produtosArmazenados);
			
		} catch (SQLException e) {
			System.out.println("Falha ao abrir a conexao");
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public static void imprimeProdutos(List<Produto> produtos) {
		System.out.println("id, nome, descricao");
		produtos.forEach(produto -> System.out.println(produto.getId() + ", " + produto.getNome() + ", " + produto.getDescricao()));
	}
}
