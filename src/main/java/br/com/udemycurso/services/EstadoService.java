package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Estado;
import br.com.udemycurso.repositories.EstadoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estRepo;
	
public List<Estado> listar(){
		
		return estRepo.findAll();
		
	}
	
	public Estado buscar(Long id){
		Estado x = validation(id);	
		return x ;
	}
     
     
	public Estado validation(Long id){
		Optional<Estado> x = estRepo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Estado não encontrada no sistema!")
				);
	}
}

