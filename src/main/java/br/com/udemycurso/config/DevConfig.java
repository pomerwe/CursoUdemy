package br.com.udemycurso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.udemycurso.services.EmailService;
import br.com.udemycurso.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Bean
	public EmailService emailService() {
		
		return new SmtpEmailService();
	}
	
}
