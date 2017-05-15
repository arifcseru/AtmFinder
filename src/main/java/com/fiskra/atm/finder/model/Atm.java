package com.fiskra.atm.finder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Atm")
public class Atm {

	@Id
    @GeneratedValue
    @Column(name = "atm_id")
    private Long id;
	
	@Column(name = "distance")
	private long distance;
	
	@Column(name = "atm_type")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
	
}
