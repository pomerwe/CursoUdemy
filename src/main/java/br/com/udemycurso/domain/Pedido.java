package br.com.udemycurso.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "IDPEDIDO", nullable=false ,unique=true)
	private Long id;
	@Column(name = "INSTANTE", nullable=false, length=128)
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime instante;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	private Pagamento pagamento;
	
	
	@ManyToOne
	@JoinColumn(name="IDCLIENTE")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="IDENDERECOENTREGA")
	private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens= new HashSet<>();
	
	public Pedido() {
		
		
	}
	
	



	public Pedido(Long id, LocalDateTime instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
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
	public LocalDateTime getInstante() {
		return instante;
	}
	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}



	public double getValorTotal() {
		double soma = 0.0;
		for(ItemPedido ip : itens) {
			
			soma=soma+ip.getSubTotal();
		}
		return soma;
	}


	public Pagamento getPagamento() {
		return pagamento;
	}





	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}





	public Cliente getCliente() {
		return cliente;
	}





	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}





	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}





	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}





	public Set<ItemPedido> getItens() {
		return itens;
	}





	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	
	
}
