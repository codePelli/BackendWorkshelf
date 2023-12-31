package ejercicios.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ejercicios.dto.Book;
import ejercicios.dto.User;

@Repository
public interface IBookDAO  extends JpaRepository<Book, Long> {
	Book findByTitle(String title);
	Book findById(int id);
	
	<T> Page<T> findByTitleContainingIgnoreCase(String title, Pageable pageable);
	
	List<Book> findListByUserId(Long userId);
	
	<T> Page<T> findByGenreIn(List<String> genres, Pageable pageable);
	
	<T> Page<T> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
	<T> Page<T> findAllByUser(User user, Pageable pageable);
	
}
