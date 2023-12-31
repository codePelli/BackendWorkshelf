package ejercicios.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import ejercicios.dto.User;
import ejercicios.service.BookServiceImpl;
import ejercicios.service.UserServiceImpl;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookServiceImpl bookService;
	
	@Autowired
	UserServiceImpl userSerice;
		
	//FOR EVERYONE USE
	@GetMapping("/all")
	public List<Book> getAll() {
		return bookService.getBooks();
	}
	
	//FOR EVERYONE USE
	@GetMapping("/detail/{id}")
	public Book getById(@PathVariable(name = "id") Long id) {
		return bookService.bookPerId(id);
	}
	
	//FOR REGISTERED USE
	@PostMapping("/add")
	public Book createBook(@RequestBody Book book) {
		book.setUser(getUserToken());
		return bookService.saveBook(book);
	}
	
	//FOR REGISTERED USE
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(name = "id") Long id, @RequestBody Book book) {
		if (getUserToken().getUserId().equals(bookService.bookPerId(id).getUser().getUserId())) {
			Book bookSelected = new Book();

			bookSelected = bookService.bookPerId(id);

			bookSelected.setTitle(book.getTitle());
			bookSelected.setAuthor(book.getAuthor());
			bookSelected.setGenre(book.getGenre());
			bookSelected.setReserved(book.getReserved());
			bookSelected.setReservationDuration(book.getReservationDuration());
			//EXPLICAR
			//bookSelected.setUser(getUserToken());
			bookSelected.setEditorial(book.getEditorial());

			bookSelected = bookService.updateBook(bookSelected);

			return new ResponseEntity<>(bookSelected, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	//ONLY ADMIN USE
	@DeleteMapping("/delete/{id}")
	public void deleteBook(@PathVariable(name = "id") Long id) {
		bookService.deleteBook(id);
	}
	
	//FOR EVERYONE USE
	// Added methods
	@GetMapping("/byTitle/{title}")
	public Book getByTitle(@PathVariable(name = "title") String title) {
		return bookService.bookPerName(title);
	}
	
    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam(name = "title") String title,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Page<Book> bookPage = bookService.searchBooksByTitle(title, PageRequest.of(page, size));
        List<Book> books = bookPage.getContent().stream().collect(Collectors.toList());

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }
    
	//FOR EVERYONE USE
	// GET /book/paginated?page=1&size=1
	@GetMapping("/paginated")
	public ResponseEntity<List<Book>> getPaginatedBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Book> bookPage = bookService.getPaginatedBook(PageRequest.of(page, size));
		List<Book> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
	}
	
	//FOR EVERYONE USE
	// GET /book/byTitlePaginated?title=Book&page=1&size=1
	@GetMapping("/byTitlePaginated")
	public ResponseEntity<List<Book>> searchByTitle(@RequestParam(name = "title") String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Book> bookPage = bookService.searchBookByTitle(title, PageRequest.of(page, size));
		List<Book> bookDTOs = bookPage.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/bookByUserId")
	public ResponseEntity<List<Book>> listByUserId(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<Book> resvId = bookService.getBookByUserId(getUserToken(), PageRequest.of(page, size));
		List<Book> pageId = resvId.getContent().stream().collect(Collectors.toList());

		return new ResponseEntity<>(pageId, HttpStatus.OK);
	}
	
	@GetMapping("/byGenres")
	public ResponseEntity<List<Book>> listByGenre(@RequestParam(name = "genres") List<String> genres,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		
		Page<Book> bookPage = bookService.getBooksByGenre(genres, PageRequest.of(page, size));
		List<Book> booksByGenre = bookPage.getContent().stream().collect(Collectors.toList());

	    if (booksByGenre.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>(booksByGenre, HttpStatus.OK);
	    }
	}
	
	public User getUserToken() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        User user = userSerice.getUser(username);
        return user;
    }

}
