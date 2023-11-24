package ejercicios.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date requestDate;
	private Date returnDate;

	@ManyToOne
	@JsonIgnoreProperties("reservation")
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("reservation")
	@JoinColumn(name = "bookId")
	private Book book;

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> ratings;

	public Reservation() {
	}



	public Reservation(Long id, Date requestDate, Date returnDate, User user, Book book) {
		this.id = id;
		this.requestDate = requestDate;
		this.returnDate = returnDate;
		this.user = user;
		this.book = book;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getRequestDate() {
		return requestDate;
	}



	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}



	public Date getReturnDate() {
		return returnDate;
	}



	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}



	@Override
	public String toString() {
		return "Reservation [id=" + id + ", requestDate=" + requestDate + ", returnDate=" + returnDate + ", user="
				+ user + ", book=" + book + "]";
	}

	
}

