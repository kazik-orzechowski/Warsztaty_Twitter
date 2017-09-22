package pl.twitter.entity;

public class GuestDTO {

	private Long guestId;
	private String guestName;
	private String guestStatus;

	public GuestDTO() {
		super();
	}

	public GuestDTO(Long guestId, String guestName, String guestStatus) {
		super();
		this.guestId = guestId;
		this.guestName = guestName;
		this.guestStatus = guestStatus;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestStatus() {
		return guestStatus;
	}

	public void setGuestStatus(String guestStatus) {
		this.guestStatus = guestStatus;
	}

	@Override
	public String toString() {
		return "GuestDTO [guestName=" + guestName + ", guestStatus=" + guestStatus + "]";
	}

}
