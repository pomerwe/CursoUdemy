package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Estado;
import br.com.udemycurso.repositories.EstadoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
public List<Estado> listar(){
		
		return repo.findAll();
		
	}
	
	public Estado buscar(Long id){
		Estado x = validation(id);	
		return x ;
	}
     
     
	public Estado validation(Long id){
		Optional<Estado> x = repo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Estado não encontrada no sistema!")
				);
	}
	
	
public Page<Estado> pesquisar(String estado, Integer page, Integer linesPerPage, String orderBy,String direction){
		
		@SuppressWarnings("deprecation")
		PageRequest pageRequest= new PageRequest(page,linesPerPage , Direction.valueOf(direction),orderBy);
		return repo.pesquisar(estado,pageRequest);
		
	}
	
}

