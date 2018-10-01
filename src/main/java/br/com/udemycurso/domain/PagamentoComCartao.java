package br.com.udemycurso.domain;

import javax.persistence.Entity;

import br.com.udemycurso.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroDeParcelas;

	public PagamentoComCartao() {
		
		// TODO Auto-generated constructor stub
	}

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas ) {
		super(id, estado, pedido);
		this.numeroDeParcelas=numeroDeParcelas;
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
	
}
