package br.com.udemycurso.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.com.udemycurso.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime dataVencimento;
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {
		
		
	}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDateTime dataVencimento,LocalDateTime dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento=dataPagamento;
		this.dataVencimento=dataVencimento;
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

	
	
	
}
