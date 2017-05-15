package com.fiskra.atm.finder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fiskra.atm.finder.model.Atm;



public interface AtmRepository extends JpaRepository<Atm,Long> {

	@Query("select a from Atm a INNER JOIN FETCH  a.address ad INNER JOIN FETCH ad.city c where c.cityName = ?1")
	List<Atm> findAtmsByCityName(String cityName);

}
