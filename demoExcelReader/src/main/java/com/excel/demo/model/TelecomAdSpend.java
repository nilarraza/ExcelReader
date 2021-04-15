package com.excel.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TelecomAdSpend {
	@Id
	@GeneratedValue
	private int id;
	private String year;
	private String total;
	private String newspaper;
	private String magazine;
	private String tv;
	private String radio;
	private String cinema;
	private String ooh;
	private String digital;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getNewspaper() {
		return newspaper;
	}
	public void setNewspaper(String newspaper) {
		this.newspaper = newspaper;
	}
	public String getMagazine() {
		return magazine;
	}
	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
	public String getTv() {
		return tv;
	}
	public void setTv(String tv) {
		this.tv = tv;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getCinema() {
		return cinema;
	}
	public void setCinema(String cinema) {
		this.cinema = cinema;
	}
	public String getOoh() {
		return ooh;
	}
	public void setOoh(String ooh) {
		this.ooh = ooh;
	}
	public String getDigital() {
		return digital;
	}
	public void setDigital(String digital) {
		this.digital = digital;
	}
	public TelecomAdSpend(int id, String year, String total, String newspaper, String magazine, String tv, String radio,
			String cinema, String ooh, String digital) {
		super();
		this.id = id;
		this.year = year;
		this.total = total;
		this.newspaper = newspaper;
		this.magazine = magazine;
		this.tv = tv;
		this.radio = radio;
		this.cinema = cinema;
		this.ooh = ooh;
		this.digital = digital;
	}
	public TelecomAdSpend() {
		super();
	}

	
	
}
