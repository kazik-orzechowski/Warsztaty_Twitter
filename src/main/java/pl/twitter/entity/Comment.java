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
 * Entity class for comments to tweets
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
	 * Comment text min = 1, max = 400
	 */
	@NotNull
	@Length(min = 1, max = 400)
	private String comment_text;
	/**
	 * Date of this comment creation
	 */
	private Date created;

	/**
	 * User that posted this comment
	 */
	@ManyToOne
	private User user;

	/**
	 * Tweet that comment refers to
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
	 * @param comment_text
	 * @param created
	 */

	public Comment(String comment_text, Date created) {
		super();
		this.comment_text = comment_text;
		this.created = created;
	}

	/**
	 * Getter
	 * 
	 * @return id of this comment
	 */

	public Long getId() {
		return id;
	}

	/**
	 * Sets id of this comment
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return this comment text
	 */
	public String getComment_text() {
		return comment_text;
	}

	/**
	 * Sets this comment text
	 * 
	 * @param comment_text
	 */
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	/**
	 * Getter
	 * 
	 * @return date of this comment creation date
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets this comment creation date
	 * 
	 * @param created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Getter
	 * 
	 * @return User that posted this comment
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets user that posted this comment
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Getter
	 * 
	 * @return tweet that this comment concerns
	 */
	public Tweet getTweet() {
		return tweet;
	}

	/**
	 * Sets tweet that this comment concerns
	 * 
	 * @param tweet
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
