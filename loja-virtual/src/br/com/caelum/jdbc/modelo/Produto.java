package br.com.caelum.jdbc.modelo;

public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Integer categoriaId;
	
	public Produto() {
		super();
	}

	public Produto(String nome, String descricao, Integer categoriaId) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.categoriaId = categoriaId;;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", categoria_id=" + categoriaId + "]";
	}
}
