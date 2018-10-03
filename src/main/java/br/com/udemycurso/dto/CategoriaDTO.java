package br.com.udemycurso.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.udemycurso.domain.Categoria;

public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="A categoria deve ter um nome obrigatoriamente")
	@Length(min=3,max=256, message="Deve possuir ao menos 3 caracteres e no máximo 256")
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
