package com.fiskra.atm.finder.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiskra.atm.finder.exception.AtmException;
import com.fiskra.atm.finder.exception.ExceptionCodes;
import com.fiskra.atm.finder.model.Address;
import com.fiskra.atm.finder.model.City;
import com.fiskra.atm.finder.repository.AddressRepository;
import com.fiskra.atm.finder.repository.CityRepository;

@RestController
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<City> getAllCityList(){
		System.out.println(context.getRealPath("uploads"));
		return cityRepository.findAllByOrderByCityNameAsc();
	}
	@RequestMapping(method = RequestMethod.GET)
	public City findCityByName(@RequestParam(value="name") String name) throws AtmException{
		City c = cityRepository.findByCityName(name);
		if(c == null)
			throw new AtmException(ExceptionCodes.CITY_NOT_FOUND);
		return c;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/top")
	public List<City> getTopOrderBy(){
		return cityRepository.findTop10ByOrderByCityNameAsc();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{name}/addresses")
	public List<Address> getAddressesByCity(@PathVariable String name){
		return addressRepository.findByCityCityName(name);
	}
	
}
