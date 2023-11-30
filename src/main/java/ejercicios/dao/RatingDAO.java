package ejercicios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicios.dto.Rating;

public interface RatingDAO extends JpaRepository<Rating,Long>{
	List<Rating> findAllByScore(int score);
}

