package com.dc3.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "id_event")
	private Integer idEvent;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Fetch(FetchMode.JOIN)
	private User user;
	private String pathImage;
	private String eventPlace;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_city")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Fetch(FetchMode.JOIN)
	private City eventCity;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_genre")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Fetch(FetchMode.JOIN)
	private Genre eventGenre;
	private String eventName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date eventDate;
	private String artistName;

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getEventPlace() {
		return eventPlace;
	}

	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}

	public City getEventCity() {
		return eventCity;
	}

	public void setEventCity(City eventCity) {
		this.eventCity = eventCity;
	}

	public Genre getEventGenre() {
		return eventGenre;
	}

	public void setEventGenre(Genre eventGenre) {
		this.eventGenre = eventGenre;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

}
