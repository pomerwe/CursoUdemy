package br.com.udemycurso.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Pedido;

@Service
public interface EmailService {

	void sendOrderEmailConfirmation(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
