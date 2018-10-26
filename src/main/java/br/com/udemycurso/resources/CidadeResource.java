package br.com.udemycurso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.domain.Cidade;
import br.com.udemycurso.resources.utils.URL;
import br.com.udemycurso.services.CidadeService;

@RestController
@RequestMapping(value="/cidade")
public class CidadeResource {
	
	@Autowired
	private CidadeService cidServ;
	
	

	@GetMapping(value="/page")
	public ResponseEntity<Page<Cidade>> listarPages(
			@RequestParam(value="cidade",defaultValue="%") String cidade,
			@RequestParam(value="idestado") Long idestado,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="cidade")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			) {
		
		
		cidade = URL.decodeString(cidade);
		Page<Cidade> cidades = cidServ.pesquisar(idestado,cidade, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.status(HttpStatus.OK).body(cidades);
		
	}
}

