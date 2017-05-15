package com.fiskra.atm.finder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiskra.atm.finder.model.City;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	/**
	 * Returns City entry whose name is given as a method parameter.
	 * @param cityname
	 * @return
	 */
	public City findByCityName(String cityname);
	
	/**
	 * Returns City entries whose id is given as a method parameter.
	 * If the entry is found, it returns true else not found.
	 * @param id
	 * @return
	 */
	public Optional<City> findByCityId(Long id);
	
	/**
	 * Returns top 3 City entry which is ordered by City name
	 * @return
	 */
	public List<City> findTop10ByOrderByCityNameAsc();
	
	
	public List<City> findAllByOrderByCityNameAsc();
	
}
