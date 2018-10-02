package br.com.udemycurso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.domain.Pedido;
import br.com.udemycurso.services.PedidoService;

@RestController
@RequestMapping(value="/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService pedServ;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		List<Pedido> categorias = pedServ.listar();
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPedido(@PathVariable("id") Long id){
		Pedido categoria = pedServ.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
	}
	
}

