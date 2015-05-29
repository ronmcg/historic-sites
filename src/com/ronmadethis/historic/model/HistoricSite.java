package com.ronmadethis.historic.model;

public class HistoricSite {

	private String nameEn, nameFr, street, plaqueLoc, town, province, reasonEn,
			reasonFr;
	private float latitude, longitude;

	public HistoricSite(String nameEn, String nameFr, String street,
			String plaqueLoc, String town, String province, String reasonEn,
			String reasonFr, float latitude, float longitude) {
		this.nameEn = nameEn;
		this.nameFr = nameFr;
		this.street = street;
		this.plaqueLoc = plaqueLoc;
		this.town = town;
		this.province = province;
		this.reasonEn = reasonEn;
		this.reasonFr = reasonFr;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public HistoricSite() {
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameFr() {
		return nameFr;
	}

	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPlaqueLoc() {
		return plaqueLoc;
	}

	public void setPlaqueLoc(String plaqueLoc) {
		this.plaqueLoc = plaqueLoc;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getReasonEn() {
		return reasonEn;
	}

	public void setReasonEn(String reasonEn) {
		this.reasonEn = reasonEn;
	}

	public String getReasonFr() {
		return reasonFr;
	}

	public void setReasonFr(String reasonFr) {
		this.reasonFr = reasonFr;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

}
