package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ibm.db2.jcc.b.c.p;

import bean.AddressBean;
import bean.BookBean;
import bean.CartBookBean;
import bean.UserBean;
public class PaymentDAO {
	Connection con;
	public Boolean processPayment(UserBean user, AddressBean addr,
			List<CartBookBean> cbb_in_cart) throws SQLException {
		// TODO Auto-generated method stub
		con = DAOConnect.getConnection();
		int addrID = addr.getAID();
		if (addr.getAID() == 0) {
			String new_address_query = "INSERT INTO Address (street, province, country, zip, phone, city) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement addAddress = con
					.prepareStatement(new_address_query);
			addAddress.setString(1, addr.getStreet());
			addAddress.setString(2, addr.getProvince());
			addAddress.setString(3, addr.getCountry());
			addAddress.setString(4, addr.getPostal());
			addAddress.setString(5, addr.getPhone());
			addAddress.setString(6, addr.getCity());
			addAddress.executeUpdate();
			addrID = DAOConnect.getLastID("Address");	
		}
		String hardCodeFail = approvalController();
		String query = "INSERT INTO PO (uid, status, address) VALUES (?, ?, ?)";
		PreparedStatement createOrder = con.prepareStatement(query);
		createOrder.setInt(1, user.getUID());
		createOrder.setString(2, hardCodeFail);
		createOrder.setInt(3, addrID);
		createOrder.executeUpdate();
		
		for (CartBookBean a : cbb_in_cart) {
			for (int i = 1; i <= a.getQuantity(); i++) {
				String addPOITEMquery = "INSERT INTO POItem (id, bid, price) VALUES (?, ?, ?)";
				PreparedStatement addPOItem = con
						.prepareStatement(addPOITEMquery);
				addPOItem.setInt(1, DAOConnect.getLastID("PO"));
				addPOItem.setString(2, a.getBook().getBid());
				addPOItem.setInt(3, a.getBook().getPrice());
				addPOItem.executeUpdate();
			}
		}
		if (hardCodeFail == "PROCESSED") {
			con.close();
			return true;
		}
		else {
			con.close();
			return false;
		}
	}
	public String approvalController() throws SQLException {
		int last_id = DAOConnect.getLastID("PO");
		last_id = last_id + 1;
		if (last_id % 3 == 0) {
			return "DENIED";
		} else {
			return "PROCESSED";
		}
	}
}
