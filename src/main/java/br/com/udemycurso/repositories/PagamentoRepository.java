package br.com.udemycurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udemycurso.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento , Long>{

}
