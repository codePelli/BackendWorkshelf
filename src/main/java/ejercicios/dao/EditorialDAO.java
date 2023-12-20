package ejercicios.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Editorial;

@Repository
public interface EditorialDAO extends JpaRepository<Editorial, Long>{
	Editorial findByEditorialName(String editorialName);
	
	<T> Page<T> findAllByeditorialNameContainingIgnoreCase(String title, Pageable pageable);
}
