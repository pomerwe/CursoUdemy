package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Endereco;
import br.com.udemycurso.repositories.EnderecoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository endRepo;
	
public List<Endereco> listar(){
		
		return endRepo.findAll();
		
	}
	
	public Endereco buscar(Long id){
		Endereco x = validation(id);	
		return x ;
	}
     
     
	public Endereco validation(Long id){
		Optional<Endereco> x = endRepo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Endereco não encontrada no sistema!")
				);
	}
}
