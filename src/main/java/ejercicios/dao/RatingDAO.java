package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ejercicios.dto.Rating;

public interface RatingDAO extends JpaRepository<Rating,Long>{

}

