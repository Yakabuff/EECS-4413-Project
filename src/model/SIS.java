package model;

import java.sql.SQLException;
import java.util.List;

import bean.BookBean;
import bean.CartBean;
import bean.ReviewBean;
import bean.UserBean;
import bean.AddressBean;
import dao.BookDAO;
import dao.ReviewDAO;
import dao.UserDAO;

public class SIS {
	private static SIS instance;
	private BookDAO bd;
	private ReviewDAO rd;
	private UserDAO ud;
	public SIS() {
		
	}
	
	
	public static SIS getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new SIS();
			instance.bd = new BookDAO();
			instance.rd = new ReviewDAO();
			instance.ud = new UserDAO();

		}
		return instance;
	}
	
	
	public List<BookBean> retrieveFromBookTitle(String title) {
		return bd.searchByTitle(title);
		
	}
	
	public BookBean retrieveFromBookID(String bid) {
		return bd.searchByBID(bid);
		
	}
	
	public List<BookBean> retrieveFromBookCategory(String category){
		
		return bd.getByCategory(category);
	}
	
	public void addToShoppingCart() {
		
	}
	
	public void removeFromShoppingCart(CartBean c, String bid) {
		c.removeFromCart(bid);
	}
	
	
	public void addReview(String name, String bid, int rating) {
		rd.addReview(rating, name, bid);
	}
	
	public List<ReviewBean> getReview(String bid) {
		return rd.getReviews(bid);
	}
	public Boolean addUser(String email, String password, String fname, String lname, String street,
			String province, String country, String zip, String phone, String city) throws Exception {
		AddressBean addr = new AddressBean (street, province, country, zip, phone, city);
		UserBean newUser = new UserBean(fname, lname, email, password, addr);
		return ud.registerUser(newUser);
	}
	public UserBean loginUser(String email, String password) throws SQLException {
		return ud.loginUser(email, password);		
	}
	

}
