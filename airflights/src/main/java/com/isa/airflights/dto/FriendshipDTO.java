package com.isa.airflights.dto;

public class FriendshipDTO {
	private Long id;
	private Long sender_id;
	private Long receiver_id;
	private Boolean accepted;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSender_id() {
		return sender_id;
	}
	public void setSender_id(Long sender_id) {
		this.sender_id = sender_id;
	}
	public Long getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(Long receiver_id) {
		this.receiver_id = receiver_id;
	}
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
	
}
