package com.med.mingdi101.hdf.consult.model;

import java.io.Serializable;
import javax.persistence.*;

import com.med.mingdi101.hdf.consult.request.UrlModel;
import com.med.mingdi101.hdf.consult.utils.SerialNoGen;

import java.sql.Timestamp;


/**
 * The persistent class for the urls database table.
 * 
 */
@Entity
@Table(name="urls")
@NamedQuery(name="Url.findAll", query="SELECT u FROM Url u")
public class Url implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String address;

	@Column(name="gmt_create")
	private Timestamp gmtCreate;

	@Column(name="serial_no")
	private String serialNo;

	private String title;

	public Url() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}