package br.com.udemycurso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.udemycurso.domain.Cliente;
import br.com.udemycurso.dto.ClienteDTO;
import br.com.udemycurso.services.ClienteService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService cliServ;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
		List<Cliente> clientes = cliServ.listar();
		List<ClienteDTO> clientesDto = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(clientesDto);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarCliente(@PathVariable("id") Long id){
		Cliente cliente = cliServ.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
		
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody ClienteDTO objDto){
		Cliente cliente = cliServ.fromDTO(objDto);
		cliente =this.cliServ.salvar(cliente);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				path("/{id}").
				buildAndExpand(cliente.getId()).
				toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable("id") Long id,@Valid @RequestBody ClienteDTO objDto){
	    Cliente cliente = cliServ.fromDTO(objDto);
		cliente =this.cliServ.atualizar(id,cliente);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				buildAndExpand(cliente.getId()).
				toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		
		this.cliServ.deletar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDTO>> listarPages(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="cliente")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			) {
		
		Page<Cliente> clientes = cliServ.listarPages(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clientesDto = clientes.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.status(HttpStatus.OK).body(clientesDto);
		
	}
	
}
