package ejercicios.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ejercicios.dto.Book;

public interface IBookService {
	
	public List<Book> getBooks();

	public Book bookPerId(Long id);

	public Book saveBook(Book book);

	public Book updateBook(Book book);

	public void deleteBook(Long id);
	
	public Book bookPerName(String name);
	
    Page<Book> getPaginatedBook(Pageable pageable);


}
