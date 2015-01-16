package com.dc3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue
	@Column(name = "id_ticket")
	private Integer idTicket;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_event")
	@Cascade(CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Fetch(FetchMode.JOIN)
	private Event event;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user")
	@Cascade(CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Fetch(FetchMode.JOIN)
	private User user;
	private String ticketName;
	private Double ticketPrice;
	private Integer ticketAmount;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	public enum Gender {
        BRANCO(" "), MASCULINO("Masculino"), FEMININO("Feminino"), UNISEX("Unissex");

        private String value;

        Gender(String str) {
            value = str;
        }

        public String toString() {
            return value;
        }
	}
	public Integer getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	public Double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Integer getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	

}
