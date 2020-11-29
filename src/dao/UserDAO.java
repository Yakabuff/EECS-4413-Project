package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibm.db2.jcc.b.c.p;

import bean.BookBean;
import bean.UserBean;

public class UserDAO {
	Connection con;
	public void registerUser(UserBean ab) throws Exception {
		con = DAOConnect.getConnection();
		if (!checkUserExists(ab.getEmail())) {
			try {
				String new_address_query = "insert into address (street, province, country, zip, phone, city) values (?, ?, ?, ?, ?, ?);";
				PreparedStatement addAddress = con.prepareStatement(new_address_query);
				addAddress.setString(1,ab.getAddressBean().getStreet());
				addAddress.setString(2, ab.getAddressBean().getProvince());
				addAddress.setString(3, ab.getAddressBean().getCountry());
				addAddress.setString(4, ab.getAddressBean().getPostal());
				addAddress.setString(5, ab.getAddressBean().getPhone());
				addAddress.setString(6, ab.getAddressBean().getCity());
				addAddress.execute();
				
				
				String new_address_id = "select max(id) as latest_id from address;";
				PreparedStatement dAddressID = con.prepareStatement(new_address_id);
				int latest_id  = dAddressID.executeQuery().getInt("LATEST_ID");
				
				
				String new_user_query = "insert into user (email, password, fname, lname, daddressid, role) values (?, ?, ?, ?, ?, ?);";
				PreparedStatement addUser = con.prepareStatement(new_user_query);
				addUser.setString(1, ab.getEmail());
				addUser.setString(2, ab.getPass());
				addUser.setString(3, ab.getFirstname());
				addUser.setString(4, ab.getLastname());
				addUser.setInt(5, latest_id);
				addUser.setString(6, "CUSTOMER");
				addUser.execute();
				System.out.println("User Added");
				}//end while loop
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			throw new Exception("Cannot register, user already exists.");
		}
		
	}
	
	public UserBean getUserDetails(String email, String pw) {
		return null;
		
	}
	
	public int loginUser(String email, String password) {
		return 0;
	}
	
	public int logoutUser(String email) {
		return 0;
	}
	
	public boolean checkUserExists(String email) {
		return false;
	}

}
