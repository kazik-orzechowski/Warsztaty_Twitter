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
	 * Empty User object constructor
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
	 * @param id
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
	 * @param username
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
	 * !!!!! If not used, to be deleted !!!!! Gets the value of field described in
	 * primary Twitter requirements
	 * 
	 * @return boolean value of enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * !!!!! If not used, to be deleted !!!!! Sets the value of field described in
	 * primary Twitter requirements
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
	 * @param email
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
	 * @param tweets
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
	 * @param comment
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
	 * @param contactsHost
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
	 * @param contactsGuest
	 */
	public void setContactsGuest(List<Contact> contactsGuest) {
		this.contactsGuest = contactsGuest;
	}

}
