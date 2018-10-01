package br.com.udemycurso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.udemycurso.services.CidadeService;

@RestController
@RequestMapping(value="/cidade")
public class CidadeResource {
	
	@Autowired
	private CidadeService cidServ;
}

