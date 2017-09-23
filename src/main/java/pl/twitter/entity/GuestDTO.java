package pl.twitter.entity;

/**
 * Entity DTO class created to collect data needed to display on host user contact list
 * @author kaz
 *
 */

public class GuestDTO {
	
	/**
	 * Gets guest user id
	 */
	private Long guestId;
	/**
	 * Gets guest user username
	 */
	private String guestName;
	/**
	 * Gets status value of Contact class object between host user and guest user 
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
	 * Getter
	 * @return Id of guest user (User class) assigned to this GuestDTO object
	 */
	public Long getGuestId() {
		return guestId;
	}

	/**
	 * Sets Id of guest user (User class) assigned to this GuestDTO object
	 * @param guestId
	 */
	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	/**
	 * Getter
	 * @return username of guest user (User class) assigned to this GuestDTO object
	 */
	public String getGuestName() {
		return guestName;
	}
	/**
	 * Sets username of guest user (User class) assigned to this GuestDTO object
	 * @param guestName
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	/**
	 * Getter
	 * @return status of guest user set by host user in contact object (Contact class)
	 */
	public String getGuestStatus() {
		return guestStatus;
	}

	/**
	 * Sets status of guest user defined by host user in contact object (Contact class)
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
