package com.dc3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue
	@Column(name = "id_genre")
	private Integer idGenre;
	private String genreName;
	public Integer getIdGenre() {
		return idGenre;
	}
	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	
}
