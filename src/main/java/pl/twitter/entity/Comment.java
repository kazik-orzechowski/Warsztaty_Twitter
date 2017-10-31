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

/**
 * Entity class for comments that my be posted to tweets
 * 
 * @author kaz
 *
 */

@Entity
@Table(name = "comments")
public class Comment {

	/**
	 * Comment id generated in TweetController
	 */

	@Id
	private Long id;
	/**
	 * Comment text 
	 */
	@NotNull
	@Length(min = 1, max = 400)
	private String comment_text;
	/**
	 * Date of this comment's creation
	 */
	private Date created;

	/**
	 * User that posted this comment
	 */
	@ManyToOne
	private User user;

	/**
	 * Tweet that this comment refers to
	 */
	@ManyToOne
	private Tweet tweet;

	/**
	 * Empty Comment object constructor
	 */

	public Comment() {

	}

	/**
	 * Comment object constructor based on two fields
	 * 
	 * @param comment_text - text of the new comment 
	 * @param created - date of creation of the new comment
	 */

	public Comment(String comment_text, Date created) {
		super();
		this.comment_text = comment_text;
		this.created = created;
	}

	/**
	 * Gets the id of this comment 
	 * 
	 * @return id of this comment
	 */

	public Long getId() {
		return id;
	}

	/**
	 * Sets the id of this comment
	 * 
	 * @param id - id of this comment
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets this comment's text
	 * 
	 * @return this comment's text
	 */
	public String getComment_text() {
		return comment_text;
	}

	/**
	 * Sets this comment text
	 * 
	 * @param comment_text - text of this comment
	 */
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	/**
	 * Gets the date of this comment creation 
	 * 
	 * @return date of this comment creation 
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets this comment creation date
	 * 
	 * @param created - date of creation of this comment
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the user that posted this comment 
	 * 
	 * @return user that posted this comment
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user that posted this comment
	 * 
	 * @param user - user that created this comment
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the tweet that this comment concerns
	 * 
	 * @return tweet that this comment concerns
	 */
	public Tweet getTweet() {
		return tweet;
	}

	/**
	 * Sets the tweet that this comment concerns
	 * 
	 * @param tweet - tweet text 
	 */
	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	/**
	 * Comment object to String method, returns Comment general description
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment_text=" + comment_text + ", created=" + created + "]";
	}

}
