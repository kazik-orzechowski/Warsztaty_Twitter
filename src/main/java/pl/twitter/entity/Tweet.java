package pl.twitter.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table (name = "tweets")
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull 
	@Length(min = 5, max = 50) //, message = "Niepoprawna dlugosc tytulu")
	private String title;
	@NotNull
	@Length(min=1, max=160)
	private String tweet_text;
	private Date created;
	@ManyToOne
	private User user;
	@OneToOne
	private Comment comment;

	
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
	public String getTweet_text() {
		return tweet_text;
	}
	public void setTweet_text(String tweet_text) {
		this.tweet_text = tweet_text;
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
	public Tweet(String title, String tweet_text, Date created, User user) {
		super();
		this.title = title;
		this.tweet_text = tweet_text;
		this.created = created;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", title=" + title + ", tweet_text=" + tweet_text + ", created=" + created
				+ ", user=" + user + "]";
	}
	public Tweet() {
		
	}
}
