package br.com.udemycurso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.udemycurso.domain.Categoria;
import br.com.udemycurso.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService catServ;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> categorias = catServ.listar();
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable("id") Long id){
		Categoria categoria = catServ.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
	
		categoria =this.catServ.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(categoria.getId()).
				toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
}
