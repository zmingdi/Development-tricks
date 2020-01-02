package com.med.mingdi101.hdf.consult.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.med.mingdi101.hdf.consult.model.Url;
import com.med.mingdi101.hdf.consult.repository.UrlRepository;
import com.med.mingdi101.hdf.consult.request.UrlModel;
import com.med.mingdi101.hdf.consult.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {

	@Autowired
	UrlRepository repo;
	@Autowired
	UrlService service;
	
	@CrossOrigin
	@PostMapping("/")
	public String addUrlSeed(UrlModel mode) {
		Url u = service.build(mode);
		return repo.save(u).getSerialNo();
	}
	@CrossOrigin
	@PostMapping("/list")
	public String addUrlSeed(@RequestBody List<UrlModel> mode) {
		return service.buildList(mode);
	}
}
