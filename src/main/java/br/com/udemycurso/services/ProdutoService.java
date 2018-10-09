package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Categoria;
import br.com.udemycurso.domain.Produto;
import br.com.udemycurso.repositories.CategoriaRepository;
import br.com.udemycurso.repositories.ProdutoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
public List<Produto> listar(){
		
		return repo.findAll();
		
	}
	
	public Produto buscar(Long id){
		Produto x = validation(id);	
		return x ;
	}
     
     
	public Produto validation(Long id){
		Optional<Produto> x = repo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Produto não encontrada no sistema!")
				);
	}
	
	public Page<Produto> pesquisar(String produto, List<Long> ids, Integer page, Integer linesPerPage, String orderBy,String direction){
		
		@SuppressWarnings("deprecation")
		PageRequest pageRequest= new PageRequest(page,linesPerPage , Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = catRepo.findAllById(ids);
		return repo.pesquisar(produto,categorias,pageRequest);
		
	}
}
