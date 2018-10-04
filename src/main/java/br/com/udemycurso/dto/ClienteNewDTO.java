package br.com.udemycurso.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.udemycurso.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Campo deve ser preenchido")
	@Length(min=3,max=256, message="Deve possuir ao menos 3 caracteres e no máximo 256")
	private String nome;	
	
	@NotEmpty(message="Campo deve ser preenchido")
	@Email(message="Email inválido")
	private String email;	
	
	@NotEmpty(message="Campo deve ser preenchido")
	private String cpf_cnpj;
	
	private Integer tipo;
	
	@NotEmpty(message="Campo deve ser preenchido")
	private String logradouro;
	
	@NotEmpty(message="Campo deve ser preenchido")
	private String numero;
	
	
	private String complemeto;
	
	private String bairro;
	
	@NotEmpty(message="Campo deve ser preenchido")
	private String cep;
	
	@NotEmpty(message="Pelo menos 1 telefone deve ser colocado para contato!")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Long idCidade;
	
	public ClienteNewDTO() {
		
		
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

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	public String getComplemeto() {
		return complemeto;
	}

	public void setComplemeto(String complemeto) {
		this.complemeto = complemeto;
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

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}
	
	
}
