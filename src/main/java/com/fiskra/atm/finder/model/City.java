package com.fiskra.atm.finder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City {

	@Id
	@GeneratedValue
	@Column(name = "city_id")
	private Long cityId;

	@Column(name = "city_name")
	private String cityName;
	
	public City() {
		// TODO Auto-generated constructor stub
	}
	
	public City(String cityName) {
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
