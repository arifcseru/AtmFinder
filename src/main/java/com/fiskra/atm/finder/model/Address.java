package com.fiskra.atm.finder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
	
	@Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "house_number")
	private String housenumber;
	
	@Column(name = "postal_code")
	private String postalcode;
	
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;	
		
	@OneToOne
	@JoinColumn(name = "geo_id")
	private GeoLocation geoLocation;	
		
	protected Address() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getHousenumber() {
		return housenumber;
	}


	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}


	public String getPostalcode() {
		return postalcode;
	}


	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}

	

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}


	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}


	@Override
    public String toString() {
        return String.format(
                "Address[id=%d, street='%s', house_number ='%s', postal_code ='%s', city= '%s']",
                id, street, housenumber, postalcode, city.getCityName());
    }

}
