package pl.twitter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import pl.twitter.entity.Tweet;

/**
 * Entity class that serves to create Twitter's user objects and to define and
 * change fields that describes users
 * 
 * @author kaz
 *
 */
@Entity
@Table(name = "twitterUsers")
public class User {

	/**
	 * User id auto generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Unique username of Twitter user, that is used as login
	 */
	@NotEmpty
	@Column(unique = true)
	private String username;
	/**
	 * User password !!!!! Password minimum requirements to be defined !!!!!
	 */
	@NotEmpty
	private String password;
	/**
	 * Field described in primary Twitter requirements, !!!!! If not used, to be
	 * deleted !!!!!
	 */
	private Boolean enabled;
	/**
	 * Twitter user's unique email
	 */
	@NotEmpty
	@Column(unique = true)
	@Email
	private String email;
	/**
	 * List of tweets posted by this user
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Tweet> tweets = new ArrayList<Tweet>();
	/**
	 * List of comments posted by this user
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Comment> comment;
	/**
	 * List of contacts in which user is recognized as a host
	 */
	@OneToMany(mappedBy = "host", cascade = CascadeType.REMOVE)
	private List<Contact> contactsHost = new ArrayList<Contact>();
	/**
	 * List of contacts in which user is recognized as a guest
	 */
	@OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE)
	private List<Contact> contactsGuest = new ArrayList<Contact>();

	/**
	 * Empty constructor
	 */
	public User() {

	}

	/**
	 * Constructor used to User creation during user registration process
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;

	}

	/**
	 * To string method using fields of user that contain single values
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", email=" + email + "]";
	}

	/**
	 * Getter
	 * 
	 * @return this user id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets this user id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return username of this Twitter user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets unique username of this Twitter user
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * !!!!! password to be encrypted !!!!!
	 * Getter
	 * 
	 * @return this user password 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * !!!!! password to be encrypted !!!!!
	 * Sets this user password during password change process
	 * 
	 * @return users password 
	 * 
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * !!!!! If not used, to be deleted !!!!!
	 * 
	 * @return value of field described in primary Twitter requirements
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * !!!!! If not used, to be deleted !!!!! 
	 * Sets value of field described in
	 * primary Twitter requirements
	 * 
	 * @param enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Getter
	 * 
	 * @return email of Twitter user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email of Twitter user
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter
	 * 
	 * @return list of user's tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * Sets list of user's tweets
	 * 
	 * @param tweets
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	/**
	 * Getter
	 * 
	 * @return list of user's comments to tweets
	 */
	public List<Comment> getComment() {
		return comment;
	}

	/**
	 * Sets list of user's comments to tweets
	 * 
	 * @param comment
	 */
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	/**
	 * Getter
	 * 
	 * @return list of contact objects (Contact class) where user is defined as a
	 *         host
	 */
	public List<Contact> getContactsHost() {
		return contactsHost;
	}

	/**
	 * Sets list of contact objects (Contact class) where user is defined as a host
	 * 
	 * @param contactsHost
	 */
	public void setContactsHost(List<Contact> contactsHost) {
		this.contactsHost = contactsHost;
	}

	/**
	 * Getter
	 * 
	 * @return list of contact objects (Contact class) where user is defined as a
	 *         guest
	 */
	public List<Contact> getContactsGuest() {
		return contactsGuest;
	}

	/**
	 * Sets Sets list of contact objects (Contact class) where user is defined as a
	 * guest
	 * 
	 * @param contactsGuest
	 */
	public void setContactsGuest(List<Contact> contactsGuest) {
		this.contactsGuest = contactsGuest;
	}

}
