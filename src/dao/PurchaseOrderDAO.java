package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.BookBean;
import bean.OrdersBean;

public class PurchaseOrderDAO {
	Connection con;
	
	public List<OrdersBean> getPurchaseOrderByBid(String bid){
		con = DAOConnect.getConnection();
		//from POItem, get all IDS that have the specified bid
		//from PO, get all rows that have that id

		
		String query = "select B.title, B.bid, B.author, B.price, B.category, P.uid, PI.id, P.address, "
				+ "A.id, A.city, A.street, A.province, A.phone, A.country, A.zip"
				+ " from BOOK B, PO P, POItem PI, Address A"
				+ " where B.bid like ? and P.uid = PI.id ";
		
				
		List<OrdersBean> orders = new ArrayList();
		try {
			PreparedStatement p = con.prepareStatement(query);

			p.setString(1, "%"+bid+"%");
			
			ResultSet r = p.executeQuery();
			
			
			while(r.next()) {
				
				String title = r.getString("title");
				System.out.println("hello");
				String author = r.getString("author");
				
				int price = r.getInt("price");
				int poId = r.getInt("uid");
				//int quantity = getOrderQuantity(poId);
				int address = r.getInt("address");
				String category = r.getString("category");
				
				String street = r.getString("street");
				String city = r.getString("city");
				String zip = r.getString("zip");
				String province = r.getString("province");
				String country = r.getString("country");
				String phone = r.getString("phone");
				System.out.println(r.getString("id")+r.getString("phone") + r.getString("street"));
				AddressBean a= new AddressBean(street, province, country, zip, phone, city);
				BookBean book = new BookBean(bid, title, author, price, category);
				
				OrdersBean order = new OrdersBean(a, book);
				
				orders.add(order);
				
			}
			
			

			
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	
	public int getOrderQuantity(int poID) {
		int quantity =0;
		con = DAOConnect.getConnection();
		String query = "SELECT COUNT(*) FROM POItem WHERE id = ?";
		PreparedStatement p;
		try {
			p = con.prepareStatement(query);
			p.setInt(1, poID);
			ResultSet r = p.executeQuery();
			if (r.next()) {
				quantity = r.getInt(1);
			}
			p.close();
			r.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quantity;
	}
}
