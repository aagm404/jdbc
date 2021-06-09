package br.com.caelum.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	
	private List<Produto> produtos = new ArrayList<>();
	
	public Categoria() {
		super();
	}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void adiciona(Produto produto) {
		produtos.add(produto);
		
	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
}
