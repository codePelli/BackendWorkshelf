package ejercicios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ejercicios.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int score;
	private String comment;

	@ManyToOne
	@JsonIgnoreProperties("reservations")
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("reservations")
	@JoinColumn(name = "bookId")
	private Book book;



	public Rating() {
	}



	public Rating(Long id, int score, String comment, User user, Book book) {
		this.id = id;
		this.score = score;
		this.comment = comment;
		this.user = user;
		this.book = book;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
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
		return "Rating [id=" + id + ", score=" + score + ", comment=" + comment + ", user=" + user + ", book=" + book
				+ "]";
	}




	
}
