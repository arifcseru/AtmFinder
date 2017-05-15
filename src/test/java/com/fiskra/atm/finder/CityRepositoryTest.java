package com.fiskra.atm.finder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiskra.atm.finder.model.City;
import com.fiskra.atm.finder.repository.CityRepository;

/**
 * 
 * @author feride.celik
 *
 * It represents test class for <class>CityRepository</class> and test classes for other repositories can be implemented.
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CityRepositoryTest {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void testFindByName(){
		
		testEntityManager.persist(new City("CORUM"));
		City city = this.cityRepository.findByCityName("CORUM");
		assertThat(city.getCityName().equals("CORUM"));
	}

}
