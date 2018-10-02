package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.repositories.ClienteRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cliRepo;
	
public List<Cliente> listar(){
		
		return cliRepo.findAll();
		
	}
	
	public Cliente buscar(Long id){
		Cliente cat = validation(id);	
		return cat ;
	}
     
     
	public Cliente validation(Long id){
		Optional<Cliente> cat = cliRepo.findById(id);
		
		return cat.orElseThrow(
				() -> new NotFoundException("Cliente não encontrada no sistema!")
				);
	}
}