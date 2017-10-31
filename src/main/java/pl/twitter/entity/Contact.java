package pl.twitter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * Entity class that serves to create objects defining reference between host
 * user (logged in) and other (guest) user. Guest user reference is used to
 * define status of users followed by host user (not followed, followed, banned)
 * and to display their tweets.
 * 
 * @author kaz
 *
 */

@Entity
@Table(name = "contacts")
public class Contact {

	/**
	 * Contact id auto generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Status of guest contact 1 - not connected, 2 - followed, 3 - banned Default
	 * status is; not followed
	 */
	@NotNull
	@Range(min = 1, max = 3)
	private int status = 1;

	/**
	 * Reference to user that the contact refers to defined as a host user
	 */
	@ManyToOne
	private User host;

	/**
	 * Reference to target contact user defined as a guest user
	 */
	@ManyToOne
	private User guest;

	/**
	 * Empty constructor
	 */

	public Contact() {
		super();

	}

	/**
	 * Gets this contact's id
	 * 
	 * @return this contact's id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id of this contact 
	 * 
	 * @param id - this contact id 
	 */

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the contact status: 1 - not connected, 2 - followed, 3 - banned
	 * 
	 * @return contact status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets this contact's status: 1 - not connected, 2 - followed, 3 - banned
	 * 
	 * @param status - status of this contact
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gets the target user of this contact defined as a guest user
	 * 
	 * @return target contact user defined as guest user
	 */
	public User getGuest() {
		return guest;
	}

	/**
	 * Sets the target contact user defined as guest user
	 * 
	 * @param guest - guest user of this contact
	 */
	public void setGuest(User guest) {
		this.guest = guest;
	}

	/**
	 * Gets the origin contact user of this contact defined as host user
	 * 
	 * @return origin contact user defined as host user
	 */
	public User getHost() {
		return host;
	}

	/**
	 * Sets the origin contact user of this contact defined as host user
	 * 
	 * @param host - host user of this contact
	 */
	public void setHost(User host) {
		this.host = host;
	}

	/**
	 * Constructor with two parameters, used when status is default
	 * 
	 * @param host - host user of this contact
	 * @param guest - guest user of this contact
	 */
	public Contact(User host, User guest) {
		super();
		this.guest = guest;
		this.host = host;
	}

	/**
	 * Constructor using three parameters, used when contact status is defined when
	 * new contact object is being created
	 * 
	 * @param status - status of this contact
	 * @param host - host user of this contact
	 * @param guest - guest user of this contact
	 */
	public Contact(int status, User host, User guest) {
		super();
		this.status = status;
		this.host = host;
		this.guest = guest;
	}

	/**
	 * To string method using all fields of Contact object
	 */
	@Override
	public String toString() {
		return "Contact [id=" + id + ", status=" + status + ", host=" + host + ", guest=" + guest + "]";
	}

}
