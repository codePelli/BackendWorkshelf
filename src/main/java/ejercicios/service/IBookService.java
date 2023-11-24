package ejercicios.service;

import java.util.List;

import ejercicios.dto.Book;

public interface IBookService {
	
	public List<Book> getBooks();

	public Book bookPerId(Long id);

	public Book saveBook(Book book);

	public Book updateBook(Book book);

	public void deleteBook(Long id);

}
