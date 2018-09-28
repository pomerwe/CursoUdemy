package br.com.udemycurso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository prodRepo;
}

