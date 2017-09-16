package pl.twitter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tweets")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Length(min = 1, max = 100) // , message = "Niepoprawna dlugosc tytulu")
	private String post;
	private Date created;
	private User user;
	private Tweet tweet;

	public Comment() {

	}

	public Comment(String post, Date created, User user, Tweet tweet) {
		super();
		this.post = post;
		this.created = created;
		this.user = user;
		this.tweet = tweet;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", post=" + post + ", created=" + created + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

}
