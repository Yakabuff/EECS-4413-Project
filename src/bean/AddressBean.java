package bean;

public class AddressBean {
	private String street, province, country, zip, phone, city;
	private int AID;
	public AddressBean (String street, String province, String country, String zip, String phone, String city, int AID) {
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
		this.city = city;
		this.AID = AID;
	}
	public AddressBean (String street, String province, String country, String zip, String phone, String city) {
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
		this.city = city;
	}
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal() {
		return zip;
	}

	public void setPostal(String postal) {
		this.zip = postal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	public int getAID() {
		return AID;
	}

	public void setAID(int AID) {
		this.AID = AID;
	}
}