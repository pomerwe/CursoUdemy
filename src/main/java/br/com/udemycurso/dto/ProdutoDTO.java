package br.com.udemycurso.dto;

import java.io.Serializable;

import br.com.udemycurso.domain.Produto;

public class ProdutoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long id;
	
	private String produto;
	
	private Double preco;
	
	public ProdutoDTO() {
		
		
	}
	
    public ProdutoDTO(Produto obj) {
    
    this.id=obj.getId();
	this.produto=obj.getProduto();
	this.preco=obj.getPreco();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
