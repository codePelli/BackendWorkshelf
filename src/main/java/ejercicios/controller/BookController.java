package ejercicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ejercicios.dto.Book;
import ejercicios.service.BookServiceImpl;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookServiceImpl bookService;
	
	@GetMapping("/all")
	public List<Book> getAll() {
		return bookService.getBooks();
	}
	
	@GetMapping("/{id}")
	public Book getById(@PathVariable(name="id") Long id) {
		return bookService.bookPerId(id);
	}
	
	@PostMapping("")
	public Book createBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable(name="id")Long id,@RequestBody Book book) {
		
		Book bookSelected = new Book();
		
		bookSeleccionada= bookService.bookPerId(id);
		
		bookSeleccionada.setTitle(book.getTitle());
		bookSeleccionada.setAuthor(book.getAuthor());
		bookSeleccionada.setBookingStatus(book.getBookingStatus());
		bookSeleccionada.setReserved(book.getReserved());
		bookSeleccionada.setReservationDate(book.getReservationDate());
		bookSeleccionada.setReservationDuration(book.getReservationDuration());
		bookSeleccionada.setUser(book.getUser());
		bookSeleccionada.setEditorial(book.getEditorial());
		
		bookSeleccionada = bookService.updateBook(bookSeleccionada);
		
		return bookSeleccionada;
	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable(name="id") Long id) {
		bookService.deleteBook(id);
	}

}
