package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cidade;
import br.com.udemycurso.repositories.CidadeRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidRepo;
	
public List<Cidade> listar(){
		
		return cidRepo.findAll();
		
	}
	
	public Cidade buscar(Long id){
		Cidade cat = validation(id);	
		return cat ;
	}
     
     
	public Cidade validation(Long id){
		Optional<Cidade> cat = cidRepo.findById(id);
		
		return cat.orElseThrow(
				() -> new NotFoundException("Cidade não encontrada no sistema!")
				);
	}
}