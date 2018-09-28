package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Categoria;
import br.com.udemycurso.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRep;

	
	public List<Categoria> listarCategorias(){
		
		return catRep.findAll();
		
	}
	
	public Categoria buscarCategoria(Long id){
		Categoria cat = validation(id);	
		return cat;
	}
     
     
	public Categoria validation(Long id){
		Optional<Categoria> cat = catRep.findById(id);
		
		
		try {
			if(cat.isPresent()) {
				return cat.get();
			}
		} 	catch (Exception e) {
				// TODO: handle exception
				return null;
		}	
		
		return null;
	}
}