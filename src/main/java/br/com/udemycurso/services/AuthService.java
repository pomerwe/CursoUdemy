package br.com.udemycurso.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.Senha;
import br.com.udemycurso.repositories.ClienteRepository;
import br.com.udemycurso.repositories.SenhaRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository cliRepo;
	
	@Autowired
	private SenhaRepository senhaRepo;
	
	@Autowired
	private BCryptPasswordEncoder bpe;

	@Autowired
	private EmailService emailService;
	
	private Random r = new Random();
	
	public void sendNewPassword(String email) {
	 Cliente cliente = cliRepo.findByEmail(email);
	 if(cliente == null) {		 
		 throw new NotFoundException("Esse email não está cadastrado no sistema!");
	 }
	Senha newPass = new Senha(null ,cliente , bpe.encode(newPassword()));	
	cliente.setSenha(newPass);
	cliRepo.save(cliente);
	senhaRepo.save(newPass);
	emailService.sendNewPasswordEmail(cliente , cliente.getSenha().getSenha());
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0 ; i<vet.length ; i++) {
			vet[i] = randomChar();
			
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = r.nextInt(3);
		if(opt == 0 ) {
			
			return (char) (r.nextInt(10) + 48);
		}
		if(opt == 1 ) {
			
			return (char) (r.nextInt(26) + 65);
		}
		
		else {
			
			return (char) (r.nextInt(26) + 97);
		}
	}

}
