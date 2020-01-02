package com.med.mingdi101.hdf.consult.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class SerialNoGen {

	public String gen(String address) {
		return DigestUtils.md5DigestAsHex(address.getBytes());
	}
}
