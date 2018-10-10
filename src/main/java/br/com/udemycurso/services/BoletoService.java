package br.com.udemycurso.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void prencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instante) {
		LocalDateTime day = instante.plusDays(7);
		pagto.setDataVencimento(day);
		
	}
	}
