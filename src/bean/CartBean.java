package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartBean {


	private int totalPrice;
	private HashMap<String, CartBookBean> cart = new HashMap();
	
	
	public CartBean() {
		
	}

	public int getPrice() {
		return totalPrice;
	}

	public void setPrice(int price) {
		this.totalPrice = price;
	}
	
	public int numBooks() {
		int num = 0;
		for(String i : cart.keySet()) {
			num = num + cart.get(i).getQuantity();
		}
		return num;
	}
	
	public void addToCart(CartBookBean book) {
		cart.put(book.getBook().getBid(), book);
		this.totalPrice = this.totalPrice + book.updatePrice();
	}
	
	//shouldve used a hashmap. too bad 
	public void removeFromCart(String bid) {
		for(String i : cart.keySet()) {
			if(cart.get(i).getBook().getBid().equals(bid)) {
				cart.remove(i);
			}
		}
	}
	
	public HashMap<String, CartBookBean> getCart() {
		return this.cart;
	}
	
	public List<CartBookBean> getCBBInCart(){
		ArrayList<CartBookBean> cart = new ArrayList();
		for(String i : this.cart.keySet()) {
			cart.add(this.cart.get(i));
		}
		
		return cart;
	}
	

}
