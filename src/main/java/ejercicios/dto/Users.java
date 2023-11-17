package ejercicios.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	 
	@Column(name = "username")
	private String username;
	 
	@Column(name = "userRole")
	private String userRole;

	@Column(name = "email", unique = true)
	private String email;
	 
	@Column(name = "userPassword")
	private String userPassword; 

	@ManyToOne
	@JoinColumn(name = "editorial_id")
	private Editorials editorials;
	
	public Users() {
		super();
	}

	public Users(Long userId, String username, String userRole, String email, String userPassword,
			Editorials editorials) {
		super();
		this.userId = userId;
		this.username = username;
		this.userRole = userRole;
		this.email = email;
		this.userPassword = userPassword;
		this.editorials = editorials;
	}

	public Editorials getEditorials() {
		return editorials;
	}

	public void setEditorials(Editorials editorials) {
		this.editorials = editorials;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	 
}
