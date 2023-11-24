package ejercicios.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bookId")
	private Long id;
	private String title;
	private String author;
	private String bookingStatus;
	private int reserved;
	private Date reservationDate;
	private Date reservationDuration;

	@ManyToOne
	@JsonIgnoreProperties("book")
	@JoinColumn(name = "ownerId")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("book")
	@JoinColumn(name = "editorialName")
	private Editorial editorial;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;


	public Book(Long id, String title, String author, String bookingStatus, int reserved, Date reservationDate,
			Date reservationDuration, User user, Editorial editorial) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.bookingStatus = bookingStatus;
		this.reserved = reserved;
		this.reservationDate = reservationDate;
		this.reservationDuration = reservationDuration;
		this.user = user;
		this.editorial = editorial;
	}


	public Book() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getBookingStatus() {
		return bookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public int getReserved() {
		return reserved;
	}


	public void setReserved(int reserved) {
		this.reserved = reserved;
	}


	public Date getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}


	public Date getReservationDuration() {
		return reservationDuration;
	}


	public void setReservationDuration(Date reservationDuration) {
		this.reservationDuration = reservationDuration;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Editorial getEditorial() {
		return editorial;
	}


	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}


	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
