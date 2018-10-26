package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Cidade;
import br.com.udemycurso.domain.Estado;
import br.com.udemycurso.repositories.CidadeRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
public List<Cidade> listar(){
		
		return repo.findAll();
		
	}
	
	public Cidade buscar(Long id){
		Cidade x = validation(id);	
		return x ;
	}
     
     
	public Cidade validation(Long id){
		Optional<Cidade> x = repo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Cidade não encontrada no sistema!")
				);
	}
	
    public Page<Cidade> pesquisar(Long idestado, String cidade, Integer page, Integer linesPerPage, String orderBy,String direction){
		
		@SuppressWarnings("deprecation")
		PageRequest pageRequest= new PageRequest(page,linesPerPage , Direction.valueOf(direction),orderBy);
		return repo.pesquisar(idestado,cidade,pageRequest);
		
	}
	
}