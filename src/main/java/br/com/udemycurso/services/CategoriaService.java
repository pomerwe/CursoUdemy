package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Categoria;
import br.com.udemycurso.dto.CategoriaDTO;
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
		updateData(catdb,categoria);
		
		return this.catRepo.save(catdb);
		
		
	}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		
		
		
	}
	
	public void deletar(Long id) {
		Categoria cat = validation(id);
		try {
			this.catRepo.deleteById(cat.getId());
			
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Não é possível excluir uma Categoria que possui Produtos!");
		}
		
	}
	public Page<Categoria> listarPages(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return catRepo.findAll(pageRequest);
		
	}
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(),objDto.getCategoria());
		
	}
}
