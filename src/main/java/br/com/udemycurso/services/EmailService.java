package br.com.udemycurso.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.Pedido;
import br.com.udemycurso.domain.Senha;

@Service
public interface EmailService {

	void sendOrderEmailConfirmation(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String senha);
}
