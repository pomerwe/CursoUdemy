package br.com.udemycurso.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {
		
		
	}

	public PagamentoComBoleto(Long id, Integer estado, Pedido pedido, LocalDateTime dataVencimento,LocalDateTime dataPagamento) {
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
