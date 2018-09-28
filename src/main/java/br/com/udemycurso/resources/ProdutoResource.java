package br.com.udemycurso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.services.ProdutoService;

@RestController
@RequestMapping(value="/produto")
public class ProdutoResource {

	@Autowired
	private ProdutoService prodServ;
}
