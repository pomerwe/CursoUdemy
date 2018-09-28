package br.com.udemycurso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository prodRepo;
}