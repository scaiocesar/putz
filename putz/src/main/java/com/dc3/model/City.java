package com.dc3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue
	@Column(name = "id_city")
	private Integer idCity;
	private String cityName;
	@ManyToOne (fetch=FetchType.EAGER )
    @JoinColumn(name="fk_state", insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
	private State state;
	public Integer getIdCity() {
		return idCity;
	}
	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	
}
