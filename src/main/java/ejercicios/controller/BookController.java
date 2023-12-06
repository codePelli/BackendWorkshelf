package ejercicios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Book getById(@PathVariable(name = "id") Long id) {
		return bookService.bookPerId(id);
	}

	@PostMapping("")
	public Book createBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable(name = "id") Long id, @RequestBody Book book) {

		Book bookSelected = new Book();

		bookSelected = bookService.bookPerId(id);

		bookSelected.setTitle(book.getTitle());
		bookSelected.setAuthor(book.getAuthor());
		bookSelected.setBookingStatus(book.getBookingStatus());
		bookSelected.setReserved(book.getReserved());
		bookSelected.setReservationDate(book.getReservationDate());
		bookSelected.setReservationDuration(book.getReservationDuration());
		bookSelected.setUser(book.getUser());
		bookSelected.setEditorial(book.getEditorial());

		bookSelected = bookService.updateBook(bookSelected);

		return bookSelected;
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable(name = "id") Long id) {
		bookService.deleteBook(id);
	}

	// Added methods
	@GetMapping("/byTitle/{title}")
	public Book getByTitle(@PathVariable(name = "title") String title) {
		return bookService.bookPerName(title);
	}

	// GET /book/paginated?page=1&size=1
	@GetMapping("/paginated")
	public ResponseEntity<List<Book>> getPaginatedBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Book> bookPage = bookService.getPaginatedBook(PageRequest.of(page, size));
		List<Book> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
	}

	// GET /book/byTitlePaginated?title=Book&page=1&size=1
	@GetMapping("/byTitlePaginated")
	public ResponseEntity<List<Book>> searchByTitle(@RequestParam(name = "title") String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Book> bookPage = bookService.searchBookByTitle(title, PageRequest.of(page, size));
		List<Book> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
	}

}
