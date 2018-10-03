package br.com.udemycurso.dto;

import java.io.Serializable;

import br.com.udemycurso.domain.Categoria;

public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String categoria;
	
	public CategoriaDTO() {
		
	}
	

	public CategoriaDTO(Categoria categoria) {
		
		this.id = categoria.getId();
		this.categoria = categoria.getNome();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
