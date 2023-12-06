package ejercicios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ejercicios.dao.IBookDAO;
import ejercicios.dto.Book;

@Service
public class BookServiceImpl implements IBookService{

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
	
	public Book bookPerName(String name) {
        return bookDAO.findByTitle(name);
    }

	@Override
	public Page<Book> getPaginatedBook(Pageable pageable) {
		// TODO Auto-generated method stub
		return bookDAO.findAll(pageable);
	}
	
	public Page<Book> searchBookByTitle(String title, Pageable pageable) {
		// TODO Auto-generated method stub
		return bookDAO.findAllByTitleContainingIgnoreCase(title, pageable);
	}
}
