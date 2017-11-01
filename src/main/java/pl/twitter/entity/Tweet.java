package pl.twitter.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity class that serves to create and modify tweets posted by users
 * @author kaz
 *
 */

@Entity
@Table (name = "tweets")
public class Tweet {

	/**
	 * Tweet's id is generated during adding new tweets (in the TweetController)
	 */
	@Id
	private Long id;
	/**
	 * Tweet's title
	 */
	@NotNull 
	@Length(min = 5, max = 50) 
	private String title;
	/**
	 * Tweet's text
	 */
	@NotNull
	@Length(min=1, max=160)
	private String tweet_text;
	/**
	 * Tweet's creation date
	 */
	private Date created;
	/**
	 * User that created this tweet
	 */
	@ManyToOne
	private User user;
	/**
	 * List of comments posted to the tweet by users
	 */
	@OneToMany (mappedBy = "tweet",  cascade=CascadeType.ALL)
	private List<Comment> comment;

	/**
	 * Gets the list of comments posted to this tweet
	 * @return list of comments posted to this tweet by users
	 */
	
	public List<Comment> getComment() {
		return comment;
	}
	
	/**
	 * Sets the list of comments posted to this tweet by users
	 * @param comment - list of comments
	 */
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	/** 
	 * Gets the id of this tweet
	 * @return id of this tweet
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Sets the id of this tweet
	 * @param id - id of this tweet
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the title of this tweet
	 * @return title of this tweet
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of this tweet
	 * @param title - title of this tweet
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Gets the text of this tweet
	 * @return text of this tweet
	 */
	public String getTweet_text() {
		return tweet_text;
	}
	/**
	 * Sets the text of this tweet
	 * @param tweet_text - text of this tweet
	 */
	public void setTweet_text(String tweet_text) {
		this.tweet_text = tweet_text;
	}
	/**
	 * Gets this tweet's date of creation
	 * @return this tweet's date of creation
	 */
	public Date getCreated() {
		return created;
	}
	
	/**
	 * Sets the date of this tweet creation
	 * @param created - date of creation of this tweet
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * Gets the user that created this tweet
	 * @return user that created this tweet
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Sets the user that created this tweet
	 * @param user - user of this tweet
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * Constructor using four parameters to define new tweet
	 * @param title - title of the tweet
	 * @param tweet_text - tesxt of the tweet
	 * @param created - date of creation of the tweet
	 * @param user - user that created the tweet
	 */
	public Tweet(String title, String tweet_text, Date created, User user) {
		super();
		this.title = title;
		this.tweet_text = tweet_text;
		this.created = created;
		this.user = user;
	}
	/**
	 * To string method using all fields of new tweet
	 */
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", title=" + title + ", tweet_text=" + tweet_text + ", created=" + created
				+ ", user=" + user + "]";
	}
	
	/**
	 * Empty constructor of the new tweet
	 */
	public Tweet() {
		
	}
}
