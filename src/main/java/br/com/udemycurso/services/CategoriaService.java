package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Categoria;
import br.com.udemycurso.repositories.CategoriaRepository;
import br.com.udemycurso.services.exceptions.BadRequestException;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepo;

	
	public List<Categoria> listar(){
		
		return catRepo.findAll();
		
	}
	
	public Categoria buscar(Long id){
		Categoria x = validation(id);	
		return x ;
	}
     
     
	public Categoria validation(Long id){
		Optional<Categoria> x = catRepo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Categoria não encontrada no sistema!")
				);
	}
	
	public Categoria salvar(Categoria categoria) {
		categoria.setId(null);
		return this.catRepo.save(categoria);
		
	}
	
	public Categoria atualizar(Long id, Categoria categoria) {
		
		Categoria catdb=buscar(id);		
		categoria.setId(catdb.getId());
		
		return this.catRepo.save(categoria);
		
		
	}
	
	public void deletar(Long id) {
		Categoria cat = validation(id);
		try {
			this.catRepo.deleteById(cat.getId());
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Não é possível excluir uma Categoria que possui Produtos!");
		}
		
	}
}
