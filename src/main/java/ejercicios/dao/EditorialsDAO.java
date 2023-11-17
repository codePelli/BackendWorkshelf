package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Editorials;

@Repository
public interface EditorialsDAO extends JpaRepository<Editorials, Long>{

}
