package pl.twitter.entity;

/**
 * Entity DTO class created to collect data needed to display on host user contact list
 * @author kaz
 *
 */

public class GuestDTO {
	
	/**
	 * Guest user id
	 */
	private Long guestId;
	/**
	 * Guest user username
	 */
	private String guestName;
	/**
	 * Status value of Contact class object between host user and guest user 
	 */
	private String guestStatus;

	/**
	 * Empty constructor
	 */
	
	public GuestDTO() {
		super();
	}

	/**
	 * Constructor using all class' fields
	 * @param guestId
	 * @param guestName
	 * @param guestStatus
	 */
	public GuestDTO(Long guestId, String guestName, String guestStatus) {
		super();
		this.guestId = guestId;
		this.guestName = guestName;
		this.guestStatus = guestStatus;
	}

	/**
	 * Gets the id of the guest user (User class) assigned to this GuestDTO object
	 * @return id of the guest user assigned to this guestDTO 
	 */
	public Long getGuestId() {
		return guestId;
	}

	/**
	 * Sets the Id of guest user (User class) assigned to this GuestDTO object
	 * @param guestId
	 */
	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	/**
	 * Gets the user name of guest user (User class) assigned to this GuestDTO object
	 * @return user name of guest user
	 */
	public String getGuestName() {
		return guestName;
	}
	/**
	 * Sets the user name of guest user (User class) assigned to this GuestDTO object
	 * @param guestName
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	/**
	 * Gets the status of guest user set by host user in contact object (Contact class)
	 * @return status of guest user 
	 */
	public String getGuestStatus() {
		return guestStatus;
	}

	/**
	 * Sets the status of guest user defined by host user in contact object (Contact class)
	 * @param guestStatus
	 */
	public void setGuestStatus(String guestStatus) {
		this.guestStatus = guestStatus;
	}
	/**
	 * Returns a string representing the data of GuestDTO in this sequence. 
	 */
	@Override
	public String toString() {
		return "GuestDTO [guestName=" + guestName + ", guestStatus=" + guestStatus + "]";
	}

}
