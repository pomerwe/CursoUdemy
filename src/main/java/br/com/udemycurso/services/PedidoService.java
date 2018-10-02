package br.com.udemycurso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.udemycurso.domain.Pedido;
import br.com.udemycurso.repositories.PedidoRepository;
import br.com.udemycurso.services.exceptions.NotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedRepo;
	
public List<Pedido> listar(){
		
		return pedRepo.findAll();
		
	}
	
	public Pedido buscar(Long id){
		Pedido x = validation(id);	
		return x ;
	}
     
     
	public Pedido validation(Long id){
		Optional<Pedido> x = pedRepo.findById(id);
		
		return x.orElseThrow(
				() -> new NotFoundException("Estado não encontrada no sistema!")
				);
	}
}
