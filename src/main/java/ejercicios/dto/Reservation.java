package ejercicios.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ejercicios.user.User;
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
	@JsonIgnoreProperties("book")
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("book")
	@JoinColumn(name = "editorial_id")
	private Editorial editorial;

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> ratings;


	public Book(Long id, String title, String author, String bookingStatus, int reserved, Date reservationDate,
			Date reservationDuration, User user, Editorial editorial) {
		super();
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


	public Reservation() {
	}


}

