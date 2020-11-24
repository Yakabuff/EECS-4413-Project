package model;

import java.util.List;

import bean.BookBean;
import dao.BookDAO;

public class SIS {
	private static SIS instance;
	private BookDAO bd;
	
	public SIS() {
		
	}
	
	
	public static SIS getInstance() throws ClassNotFoundException{
		if(instance == null) {
			instance = new SIS();
			instance.bd = new BookDAO();

		}
		return instance;
	}
	
	
	public List<BookBean> retrieveFromBookTitle(String title) {
		return bd.searchByTitle(title);
		
	}
	
	public List<BookBean> retrieveFromBookID(String bid) {
		return bd.searchByBID(bid);
		
	}
	
	public List<BookBean> retrieveFromBookCategory(String category){
		
		return bd.getByCategory(category);
	}
	
	public void addToShoppingCart() {
		
	}
	

}
