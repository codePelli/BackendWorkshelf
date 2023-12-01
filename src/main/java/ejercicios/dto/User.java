package ejercicios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	 
	@Column(name = "username")
	private String username;

	@Column(name = "email", unique = true)
	private String email;
	 
	@Column(name = "userPassword")
	private String password;

	
	@ManyToOne
	@JsonIgnoreProperties("users")
	@JoinColumn(name = "roleId")
	private Role role;
	
	
	public User() {
		super();
	}

	public User(Long userId, String username, String userRole, String email, String password,
			Editorial editorials) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String userPassword) {
		this.password = userPassword;
	}
	 
}
