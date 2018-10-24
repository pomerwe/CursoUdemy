package br.com.udemycurso.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.Pedido;

@Service
public abstract class AbstractEmailService implements EmailService{

	@Value("${default.email.sender}")
	private String sender;
	
	@Override
	public void sendOrderEmailConfirmation(Pedido obj) {
		SimpleMailMessage msg = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(obj.getCliente().getEmail());
		msg.setFrom(sender);
		msg.setSubject("Pedido confirmado! Código: " + obj.getId());
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText(obj.toString());
		return msg;
	}
	
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String senha) {
		SimpleMailMessage msg = prepareNewPasswordEmail(cliente,senha);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String senha) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(cliente.getEmail());
		msg.setFrom(sender);
		msg.setSubject("Nova senha de acesso!");
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText("Email: " + cliente.getEmail() + " \n Senha: " + cliente.getSenha().getSenha());
		return msg;
	}
	
}
