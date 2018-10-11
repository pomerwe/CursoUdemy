package br.com.udemycurso.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "IDENDERECO", nullable=false ,unique=true)
	private Long id;
	
	@Column(name = "LOGRADOURO", nullable=false, length=128)
	private String logradouro;
	
	@Column(name = "NUMERO", nullable=false, length=256)
	private String numero;
	
	@Column(name = "COMPLEMENTO", nullable=true, length=256)
	private String complemento;
	
	@Column(name = "BAIRRO", nullable=true, length=256)
	private String bairro;
	
	@Column(name = "CEP", nullable=false, length=256)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name="IDCIDADE", nullable=false)
	private Cidade cidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="IDCLIENTE", nullable=false)
	private Cliente cliente;
	

	public Endereco() {
		
		
	}
	
	
	public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade,Cliente cliente) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade=cidade;
		this.cliente= cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemeto(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
