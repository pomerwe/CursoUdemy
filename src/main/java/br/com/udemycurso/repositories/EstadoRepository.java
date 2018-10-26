package br.com.udemycurso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.udemycurso.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado , Long>{
	
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Estado obj WHERE obj.estado LIKE %:estado%")
	Page<Estado> pesquisar(@Param("estado") String estado , Pageable pageRequest);
}