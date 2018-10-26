package br.com.udemycurso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.domain.Estado;
import br.com.udemycurso.resources.utils.URL;
import br.com.udemycurso.services.EstadoService;

@RestController
@RequestMapping(value="/estado")
public class EstadoResource {

	@Autowired
	private EstadoService estServ;
	
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<Estado>> pesquisar(
			@RequestParam(value="estado" , defaultValue="%") String estado,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="estado")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			
			){
		
		estado = URL.decodeString(estado);
		Page<Estado> estados = estServ.pesquisar(estado, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(estados);
		
		
	}
	
	
	
	
}

