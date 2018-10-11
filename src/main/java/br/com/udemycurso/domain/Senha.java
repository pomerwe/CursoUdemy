package br.com.udemycurso.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Senha implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSenha;
	
	@OneToOne
	@JoinColumn(name="idcliente",nullable=false,unique=true)
	private Cliente cliente;
	
	@JsonIgnore
	@Column(name="senha",nullable=false ,length=256)
	private String senha;

	public Senha() {
		
	}
	
	
	
	
	public Senha(Long idSenha, Cliente cliente, String senha) {
		super();
		this.idSenha = idSenha;
		this.cliente = cliente;
		this.senha = senha;
	}




	public Long getIdSenha() {
		return idSenha;
	}

	public void setIdSenha(Long idSenha) {
		this.idSenha = idSenha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}