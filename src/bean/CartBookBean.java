package bean;

public class CartBookBean {
	int quantity;
	BookBean book;
	
	public CartBookBean(int quantity, BookBean book) {
		super();
		this.quantity = quantity;
		this.book = book;
	}
	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the book
	 */
	public BookBean getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(BookBean book) {
		this.book = book;
	}
	
	public int updatePrice() {
		return this.quantity * this.book.getPrice();
	}
	
	
}
