package skprojekat.ticketservice.dto;

public class TicketCreateDto {

	private Integer flightId;
	
	private Integer userId;

	private String status;

	private Double price;

	public TicketCreateDto(Integer flightId, Integer userId, String status, Double price) {
		this.flightId = flightId;
		this.userId = userId;
		this.status = status;
		this.price = price;
	}
	
	public TicketCreateDto() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
