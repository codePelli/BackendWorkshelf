package ejercicios.dto;

import java.util.ArrayList;
import java.util.Date;

/*
 * RESERVED 0 = AVAILABLE
 * RESERVED 1 = RESERVED
 * RESERVED 2 = LOST
 * RESERVED 3 = NOT AVAILABLE
 */

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
	private String image;
	private String author;
	private int reserved;
	private int reservationDuration;

	@ManyToOne
	@JsonIgnoreProperties("books")
	@JoinColumn(name = "ownerId")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("book")
	@JoinColumn(name = "editorialName")
	private Editorial editorial;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations = new ArrayList<>();


	public Book(Long id, String title, String image, String author, int reserved,
			int reservationDuration, User user, Editorial editorial) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.author = author;
		this.reserved = reserved;
		this.reservationDuration = reservationDuration;
		this.user = user;
		this.editorial = editorial;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
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


	public int getReserved() {
		return reserved;
	}


	public void setReserved(int reserved) {
		this.reserved = reserved;
	}


	public int getReservationDuration() {
		return reservationDuration;
	}


	public void setReservationDuration(int reservationDuration) {
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
