package br.com.udemycurso.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemycurso.domain.Cidade;
import br.com.udemycurso.domain.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade , Long>{
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Cidade obj INNER JOIN obj.estado WHERE obj.estado.id=:estado and obj.cidade LIKE %:cidade%")
	Page<Cidade> pesquisar(@Param("estado") Long idestado ,@Param("cidade") String cidade,  Pageable pageRequest);
	
}

