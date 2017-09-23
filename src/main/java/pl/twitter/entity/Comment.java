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
 * @author kaz
 *
 */

@Entity
@Table(name = "comments")
public class Comment {

	/**
	 * Comment id generated in Tweeter Controller 
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
	 * Date of comment creation
	 */
	private Date created;
	
	/**
	 * User that posted a comment
	 */
	@ManyToOne
	private User user;
	
	/**
	 * Tweet that comment refers to
	 */
	@ManyToOne
	private Tweet tweet;
	
	/**
	 * Empty comment constructor
	 */
	
	public Comment() {

	}

	/**
	 * Comment constructor from fields
	 * @param comment_text
	 * @param created
	 */
	
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

	/**
	 * Comment to String method, returns Comment general description
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment_text=" + comment_text + ", created=" + created + "]";
	}

	
}
