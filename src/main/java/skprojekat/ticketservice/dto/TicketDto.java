package skprojekat.ticketservice.dto;

public class TicketDto {

	private Integer flightId;
	
	private Integer userId;

	public TicketDto(Integer flightId, Integer userId) {
		this.flightId = flightId;
		this.userId = userId;
	}
	
	public TicketDto() {
		
	}
	
	/**
	 * @return the flightId
	 */
	public Integer getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
