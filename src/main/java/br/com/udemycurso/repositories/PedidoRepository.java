package br.com.udemycurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udemycurso.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido , Long>{
	
}