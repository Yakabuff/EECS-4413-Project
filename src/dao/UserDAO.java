package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ibm.db2.jcc.b.c.p;

import bean.AddressBean;
import bean.BookBean;
import bean.UserBean;

public class UserDAO {
	Connection con;
	public Boolean registerUser(UserBean ab) throws Exception {
		if (!checkUserExists(ab.getEmail())) {
			try {
				con = DAOConnect.getConnection();
				String new_address_query = "INSERT INTO Address (street, province, country, zip, phone, city) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement addAddress = con.prepareStatement(new_address_query);
				addAddress.setString(1,ab.getAddressBean().getStreet());
				addAddress.setString(2, ab.getAddressBean().getProvince());
				addAddress.setString(3, ab.getAddressBean().getCountry());
				addAddress.setString(4, ab.getAddressBean().getPostal());
				addAddress.setString(5, ab.getAddressBean().getPhone());
				addAddress.setString(6, ab.getAddressBean().getCity());
				addAddress.executeUpdate();
				
				
				String new_address_id = "select max(id) as latest_id from address";
				PreparedStatement dAddressID = con.prepareStatement(new_address_id);
				ResultSet rs  = dAddressID.executeQuery();
				int latest_id = 0;
				while (rs.next()) {
					latest_id = rs.getInt("LATEST_ID");
				}
				
				String new_user_query = "insert into user (email, password, fname, lname, daddressid, role) values (?, ?, ?, ?, ?, ?)";
				PreparedStatement addUser = con.prepareStatement(new_user_query);
				addUser.setString(1, ab.getEmail());
				addUser.setString(2, ab.getPass());
				addUser.setString(3, ab.getFirstname());
				addUser.setString(4, ab.getLastname());
				addUser.setInt(5, latest_id);
				addUser.setString(6, "CUSTOMER");
				addUser.executeUpdate();
				con.close();
				return true;
				}//end while loop
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con.close();
			}
		}
		else {
			throw new Exception("Cannot register, user already exists.");
		}
		return false;
		
	}
	
	
	public UserBean loginUser(String email, String password) throws SQLException {
		con = DAOConnect.getConnection();
		UserBean user = null;
		AddressBean address = null;
		String query = "select * from user where email = ? and password = ?";
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, email);
		p.setString(2, password);
		ResultSet r = p.executeQuery();

		while (r.next()) {
			int uid = r.getInt("id");
			String fname = r.getString("fname");
			String lname = r.getString("lname");
			String role = r.getString("role");
			int id = r.getInt("daddressid");
			String addrquery = "select * from address where id = ?";
			PreparedStatement addrp = con.prepareStatement(addrquery);
			addrp.setInt(1, id);
			ResultSet addr = addrp.executeQuery();
			while (addr.next()) {
				address = new AddressBean(addr.getString("street"), addr.getString("province"), addr.getString("country"), addr.getString("zip"), addr.getString("phone"), addr.getString("city"), addr.getInt("id"));
			}
			user = new UserBean(fname, lname, email, password, address, uid, role);
		}

		r.close();
		p.close();
		con.close();

		return user;
	}
	
	
	public boolean checkUserExists(String email) throws SQLException {
		con = DAOConnect.getConnection();
		String checkUserExists = "select count(*) as count from user where email='" + email + "'";
		PreparedStatement p = con.prepareStatement(checkUserExists);
		ResultSet r = p.executeQuery();
		int count = 0;
		while (r.next()) {
			count = r.getInt("count");
		}
		p.close();
		r.close();
		con.close();
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

}
