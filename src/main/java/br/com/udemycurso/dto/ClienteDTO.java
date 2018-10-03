package br.com.udemycurso.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.udemycurso.domain.Cliente;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Campo deve ser preenchido")
	@Length(min=3,max=256, message="Deve possuir ao menos 3 caracteres e no máximo 256")
	private String nome;
	
	@NotEmpty(message="Campo deve ser preenchido")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}
	

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
