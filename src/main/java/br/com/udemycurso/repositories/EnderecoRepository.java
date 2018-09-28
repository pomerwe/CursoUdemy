package br.com.udemycurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udemycurso.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco , Long>{
	
}
