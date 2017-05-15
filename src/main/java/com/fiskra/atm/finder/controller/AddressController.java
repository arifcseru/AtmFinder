package com.fiskra.atm.finder.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiskra.atm.finder.model.Address;
import com.fiskra.atm.finder.repository.AddressRepository;

@RestController
@RequestMapping("/address")
public class AddressController {

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<Address> getAllAddressList(){
		return addressRepository.findAll();
	}
	
}
