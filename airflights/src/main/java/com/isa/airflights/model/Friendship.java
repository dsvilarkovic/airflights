package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "friendship")
public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//TODO: dodati kad @Djuka sredi svoje
	@ManyToOne(optional = false)
	@NonNull
	private AbstractUser sender;
	
	//TODO: dodati kad @Djuka sredi svoje
	@ManyToOne(optional = false)
	@NonNull
	private AbstractUser receiver;
	
	@Column(name = "accepted")
	private Boolean accepted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractUser getSender() {
		return sender;
	}

	public void setSender(AbstractUser sender) {
		this.sender = sender;
	}

	public AbstractUser getReceiver() {
		return receiver;
	}

	public void setReceiver(AbstractUser receiver) {
		this.receiver = receiver;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
	
}
