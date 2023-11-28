package ejercicios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Book;

@Repository
public interface IBookDAO  extends JpaRepository<Book, Long> {
	Book findByTitle(String title);
}
