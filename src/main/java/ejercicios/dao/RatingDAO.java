package ejercicios.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Rating;

@Repository
public interface RatingDAO extends JpaRepository<Rating,Long>{
	List<Rating> findAllByScore(int score);
	
	<T> Page<T> findAllByScore(Integer rating, Pageable pageable);
}

