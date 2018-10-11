package br.com.udemycurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.udemycurso.domain.Senha;

@Repository
public interface SenhaRepository extends JpaRepository<Senha , Long>{

}
