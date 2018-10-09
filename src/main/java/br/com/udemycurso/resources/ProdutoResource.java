package br.com.udemycurso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.domain.Produto;
import br.com.udemycurso.dto.ProdutoDTO;
import br.com.udemycurso.resources.utils.URL;
import br.com.udemycurso.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService prodServ;
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ProdutoDTO>> listarPages(
			@RequestParam(value="nome",defaultValue="%") String produtoUri,
			@RequestParam(value="categorias",defaultValue="%") String categoriasUri,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="categoria")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction
			) {
		
		String produto = URL.decodeString(produtoUri);
		List<Long> ids = URL.decodeLongList(categoriasUri);
		Page<Produto> produtos = prodServ.pesquisar(produto,ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtosDto = produtos.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.status(HttpStatus.OK).body(produtosDto);
		
	}
}
