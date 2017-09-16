package pl.twitter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import pl.twitter.entity.Tweet;

@Entity
@Table(name = "twitterUsers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotNull
	private Boolean enabled;
	@NotEmpty
	@Email
	private String email;
	@OneToMany (mappedBy="user",cascade=CascadeType.ALL)
	private List<Tweet> tweets = new ArrayList<Tweet>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", email=" + email + "]";
	}
	
	public User() {
		
	}
	
	public User(String username, String password, Boolean enabled, String email, List<pl.twitter.entity.Tweet> tweets) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.tweets = tweets;
	}
	
}
