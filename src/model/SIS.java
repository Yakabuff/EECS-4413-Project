package model;

import java.util.List;

import bean.BookBean;
import bean.CartBean;
import bean.ReviewBean;
import dao.BookDAO;
import dao.ReviewDAO;

public class SIS {
	private static SIS instance;
	private BookDAO bd;
	private ReviewDAO rd;
	public SIS() {
		
	}
	
	
	public static SIS getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new SIS();
			instance.bd = new BookDAO();
			instance.rd = new ReviewDAO();

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
	
	

}
