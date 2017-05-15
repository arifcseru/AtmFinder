package com.fiskra.atm.finder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiskra.atm.finder.model.Atm;
import com.fiskra.atm.finder.repository.AtmRepository;

@RestController
@RequestMapping("/atm")
public class AtmController {
	

	@Autowired
	private AtmRepository atmRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<Atm> getAllAtmList() {
		return atmRepository.findAll();
	}

	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Atm> getAllAtmListByCity(@RequestParam(value="name") String name){
		return atmRepository.findAtmsByCityName(name);
	}

}
