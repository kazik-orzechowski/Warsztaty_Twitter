package pl.twitter.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Length(min = 1, max = 200, message = "Niepoprawna dlugosc postu")
	private String comment_text;
	@NotNull
	private Date created;
	@ManyToOne
	private User user;
	@ManyToOne
	private Tweet tweet;
	
	
	
	public Comment() {

	}

	
	
	public Comment(String comment_text, Date created) {
		super();
		this.comment_text = comment_text;
		this.created = created;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment_text=" + comment_text + ", created=" + created + "]";
	}

	
}
