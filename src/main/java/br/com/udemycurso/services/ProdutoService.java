package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Produto;
import br.com.udemycurso.repositories.ProdutoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository prodRepo;
	
public List<Produto> listar(){
		
		return prodRepo.findAll();
		
	}
	
	public Produto buscar(Long id){
		Produto x = validation(id);	
		return x ;
	}
     
     
	public Produto validation(Long id){
		Optional<Produto> x = prodRepo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Produto não encontrada no sistema!")
				);
	}
}
