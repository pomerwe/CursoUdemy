package br.com.udemycurso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.udemycurso.domain.Categoria;
import br.com.udemycurso.dto.CategoriaDTO;
import br.com.udemycurso.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService catServ;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		List<Categoria> categorias = catServ.listar();
		List<CategoriaDTO> categoriasDto = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(categoriasDto);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable("id") Long id){
		Categoria categoria = catServ.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody CategoriaDTO objDto){
		Categoria categoria = catServ.fromDTO(objDto);
		categoria =this.catServ.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(categoria.getId()).
				toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value="/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable("id") Long id,@Valid @RequestBody CategoriaDTO objDto){
	    Categoria categoria = catServ.fromDTO(objDto);
		categoria =this.catServ.atualizar(id,categoria);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				buildAndExpand(categoria.getId()).
				toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		
		this.catServ.deletar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoriaDTO>> listarPages(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="categoria")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			) {
		
		Page<Categoria> categorias = catServ.listarPages(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriasDto = categorias.map(categoria -> new CategoriaDTO(categoria));
		return ResponseEntity.status(HttpStatus.OK).body(categoriasDto);
		
	}
	
}
