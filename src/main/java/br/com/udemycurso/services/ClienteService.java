package br.com.udemycurso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository prodRepo;
}
