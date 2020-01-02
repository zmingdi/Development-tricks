package com.med.mingdi101.hdf.consult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.med.mingdi101.hdf.consult.model.Url;
import com.med.mingdi101.hdf.consult.repository.UrlRepository;
import com.med.mingdi101.hdf.consult.request.UrlModel;
import com.med.mingdi101.hdf.consult.utils.SerialNoGen;

@Service
public class UrlService {

	@Autowired
	SerialNoGen generator;
	
	@Autowired
	UrlRepository repo;
	public Url build(UrlModel model) {
		Url u = new Url();
		u.setAddress(model.getAddress());
		u.setTitle(model.getTitle().substring(0,model.getTitle().length()<255?model.getTitle().length(): 255));
		u.setSerialNo(generator.gen(u.getAddress()));
		return u;
	}
	public String buildList(List<UrlModel> list) {
		List<Url> ul = Lists.newArrayList();
		list.stream().forEach(m->ul.add(build(m)));
		try {
			return ""+repo.saveAll(ul).size();
		} catch (DataIntegrityViolationException e) {
			return "0";
		}
		
	}
}
