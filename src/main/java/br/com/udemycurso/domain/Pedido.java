package br.com.udemycurso.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "IDPEDIDO")
	private Long id;
	@Column(name = "INSTANTE", nullable=false, length=128)
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@Column(name = "VALORTOTAL")
	private Double valorTotal;
	
	
	
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
	
	



	public Pedido(Long id, Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		
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
	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
	}



	public void setValorTotal(List<ItemPedido> itens) {
		double soma = 0.0;
		for(ItemPedido ip : itens) {
			
			soma=(soma+ip.getSubTotal());
		}
		this.valorTotal = soma;
	}


	public Double getValorTotal() {
		
		return this.valorTotal;
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





	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt" , "BR"));
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Nº pedido: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(stf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome() + " " + getCliente().getCpfcnpj());
		builder.append(", Situação do Pagamento: ");
		builder.append("\n Detalhes \n ");
		for(ItemPedido x : getItens()) {
			builder.append(x.toString());
		}
		builder.append("\n Valor total: ");
		builder.append(getValorTotal());
		
		return builder.toString();
	}

	
	
	
	
}
