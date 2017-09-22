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

@Entity
@Table(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 1 - not connected, 2 - followed, 3 - banned
	@NotNull
	@Range(min = 1, max = 3)
	private int status = 1;
	@ManyToOne
	private User host;
	@ManyToOne
	private User guest;

	public Contact() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public Contact(User host, User guest) {
		super();
		this.guest = guest;
		this.host = host;
	}

	public Contact(int status, User host, User guest) {
		super();
		this.status = status;
		this.host = host;
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", status=" + status + ", host=" + host + ", guest=" + guest + "]";
	}

}
