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
	 * !!!! Field to be used after introducing an encrypted login !!!!
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
	 * List of tweets posted by the user
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Tweet> tweets = new ArrayList<Tweet>();
	/**
	 * List of comments posted by the user
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Comment> comment;
	/**
	 * List of contacts in which a user is recognized as a host
	 */
	@OneToMany(mappedBy = "host", cascade = CascadeType.REMOVE)
	private List<Contact> contactsHost = new ArrayList<Contact>();
	/**
	 * List of contacts in which a user is recognized as a guest
	 */
	@OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE)
	private List<Contact> contactsGuest = new ArrayList<Contact>();

	/**
	 * Empty User object constructor
	 */
	public User() {

	}

	/**
	 * Constructor used to User creation during user registration process
	 * 
	 * @param username - this user's user name
	 * @param password - this user's password
	 * @param email - this user's e-mail address
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
	 * Gets this user's id
	 * 
	 * @return this user's id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets this user's id
	 * 
	 * @param id - this user's id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user name of this user
	 * 
	 * @return user name of this user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the unique user name of this user
	 * 
	 * @param username - this user's user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * !!!!! password to be encrypted !!!!! 
	 * Gets this user's password
	 * 
	 * @return this user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * !!!!! password to be encrypted !!!!! 
	 * Sets this user's password during
	 * password change process
	 * 
	 * @return users password
	 * 
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * !!!!! To be used to mark user that is logged in !!!!! 
	 * 
	 * @return boolean value of enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * !!!!! To be used to mark user that is logged in !!!!! 
	 * 
	 * @param enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the email of this user
	 * 
	 * @return email of this user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of this user
	 * 
	 * @param email - this user's e-mail address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the list of this user's tweets
	 * 
	 * @return list of this user's tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * Sets the list of this user's tweets
	 * 
	 * @param tweets - this user's tweets
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	/**
	 * Gets the list of this user's comments to tweets
	 * 
	 * @return list of this user's comments to tweets
	 */
	public List<Comment> getComment() {
		return comment;
	}

	/**
	 * Sets the list of this user's comments to tweets
	 * 
	 * @param comment - this user's comment
	 */
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	/**
	 * Gets the list of contact objects (Contact class) where user is defined as a
	 * host
	 * 
	 * @return list of this user contacts (user = host)
	 */
	public List<Contact> getContactsHost() {
		return contactsHost;
	}

	/**
	 * Sets the list of contact objects (Contact class) where user is defined as a
	 * host
	 * 
	 * @param contactsHost - this user contacts, where user acts as a host
	 */
	public void setContactsHost(List<Contact> contactsHost) {
		this.contactsHost = contactsHost;
	}

	/**
	 * Gets the list of this user's contact objects (Contact class) where user is
	 * defined as a guest
	 * 
	 * @return list of this user's contact (user = guest)
	 */
	public List<Contact> getContactsGuest() {
		return contactsGuest;
	}

	/**
	 * Sets the list of this user's contact objects (Contact class) where user is
	 * defined as a guest
	 * 
	 * @param contactsGuest - this user's contacts where user acts as a guest
	 */
	public void setContactsGuest(List<Contact> contactsGuest) {
		this.contactsGuest = contactsGuest;
	}

}
