package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ejercicios.dao.IBookDAO;
import ejercicios.dto.Book;

@Service
public class BookServiceImpl {

	@Autowired
	IBookDAO bookDAO;

	public List<Book> getBooks() {
		return bookDAO.findAll();
	}
	
	public Book bookPerId(Long id) {
		return bookDAO.findById(id).get();
	}
	
	public Book saveBook(Book book) {
		return bookDAO.save(book);
	}
	
	public Book updateBook(Book book) {
		return bookDAO.save(book);
	}
	
	public void deleteBook(Long id) {
		bookDAO.deleteById(id);
	}
}
