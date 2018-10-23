package br.com.udemycurso.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.udemycurso.domain.ItemPedido;
import br.com.udemycurso.domain.Pagamento;

public class PedidoDTO {

	private Long id;
	
	private Date instante;
		
	private Pagamento pagamento;
	
	private Long idcliente;
	
	private Long idenderecoDeEntrega;

	private Set<ItemPedido> itens= new HashSet<>();

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

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}


	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Long getIdenderecoDeEntrega() {
		return idenderecoDeEntrega;
	}

	public void setIdenderecoDeEntrega(Long idenderecoDeEntrega) {
		this.idenderecoDeEntrega = idenderecoDeEntrega;
	}
	
	
	
	
}
