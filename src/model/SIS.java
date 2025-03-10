package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import bean.BookBean;
import bean.CartBean;
import bean.CartBookBean;
import bean.OrdersBean;
import bean.ReviewBean;
import bean.UserBean;
import bean.AddressBean;
import dao.AnalyticsDAO;
import dao.BookDAO;
import dao.PaymentDAO;
import dao.PurchaseOrderDAO;
import dao.ReviewDAO;
import dao.UserDAO;

public class SIS {
	private static SIS instance;
	private BookDAO bd;
	private ReviewDAO rd;
	private UserDAO ud;
	private PaymentDAO pd;
	private PurchaseOrderDAO po;
	private AnalyticsDAO ad;
	public SIS() {
		
	}
	
	
	public static SIS getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new SIS();
			instance.bd = new BookDAO();
			instance.rd = new ReviewDAO();
			instance.ud = new UserDAO();
			instance.pd = new PaymentDAO();
			instance.po = new PurchaseOrderDAO();
			instance.ad = new AnalyticsDAO();

		}
		return instance;
	}
	
	public AnalyticsDAO getAnalyticsDAO(){
		return this.ad;
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
	
	
	public void addReview(String name, String bid, int rating, String message) {
		rd.addReview(rating, name, bid, message);
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


	public Boolean processPayment(UserBean user, AddressBean addr, List<CartBookBean> cbb_in_cart) throws Exception {
		return pd.processPayment(user, addr, cbb_in_cart);
	}
	
	public List<OrdersBean> getOrdersByBid (String bid){
		return po.getPurchaseOrderByBid(bid);
	}
	
	public Map<String, Integer> getPurchasesForAllMonths(){
		return ad.getPurchasesForAllMonths();
	}

}
