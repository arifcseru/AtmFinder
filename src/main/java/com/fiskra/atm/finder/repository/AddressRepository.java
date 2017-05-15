package com.fiskra.atm.finder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiskra.atm.finder.model.Address;


public interface AddressRepository extends JpaRepository<Address,Long> {
	
	/**
	 * Retrieves all atm addresses in city
	 * 
	 * @param cityname
	 * @return
	 */
	public List<Address> findByCityCityName(String cityname);
	

}
