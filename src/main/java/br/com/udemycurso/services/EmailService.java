package br.com.udemycurso.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.udemycurso.domain.Pedido;

public interface EmailService {

	void sendOrderEmailConfirmation(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
