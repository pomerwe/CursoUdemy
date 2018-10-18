package br.com.udemycurso.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.domain.Pedido;
import br.com.udemycurso.domain.Produto;
import br.com.udemycurso.dto.ProdutoDTO;
import br.com.udemycurso.resources.utils.URL;
import br.com.udemycurso.services.PedidoService;

@RestController
@RequestMapping(value="/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService pedServ;
	
//	@GetMapping
//	public ResponseEntity<List<Pedido>> listar() {
//		List<Pedido> categorias = pedServ.listar();
//		return ResponseEntity.status(HttpStatus.OK).body(categorias);
//		
//	}
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPedido(@PathVariable("id") Long id){
		Pedido categoria = pedServ.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Pedido obj){

		obj =this.pedServ.salvar(obj);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(obj.getId()).
				toUri();
		return ResponseEntity.created(uri).build();
		
	}
	@GetMapping
	public ResponseEntity<Page<Pedido>> listarPages(			
		    @RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="categoria")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			) {
		
	
		Page<Pedido> list = pedServ.pesquisar(page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
	
}

