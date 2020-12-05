package bean;

public class UserBean {
	private String firstname, lastname, username, email, pass , role;
	private int UID;
	private AddressBean addrbean;
	
	public UserBean(String firstName, String lastName, String email, String password, AddressBean addrbean, int UID, String role) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.pass = password;
		this.addrbean = addrbean;
		this.UID = UID;
		this.role = role;
	}
	
	public UserBean(String fname, String lname, String email, String password,
			AddressBean addr) {
		this.firstname = fname;
		this.lastname = lname;
		this.email = email;
		this.pass = password;
		this.addrbean = addr;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public AddressBean getAddressBean() {
		return addrbean;
	}
	public void setAddressBean(AddressBean addrbean) {
		this.addrbean = addrbean;
	}
	public int getUID() {
		return UID;
	}
	public void setUID(int UID) {
		this.UID = UID;
	}
}
