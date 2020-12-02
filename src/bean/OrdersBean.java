package bean;

public class OrdersBean {
	public AddressBean address;
	public BookBean book;


	public OrdersBean(AddressBean a, BookBean b) {
		this.address=a;
		this.book = b;

	}
	
	public OrdersBean() {
		
	}

	/**
	 * @return the address
	 */
	public AddressBean getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressBean address) {
		this.address = address;
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
	
//	@Override
//	public String toString() {
//	    return "OrdersBean [title=" + title + 
//	    		", bid=" + bid + 
//	    		", price=" + price +
//	    		", category=" + category +
//	    		", author=" + author +
//	    		", quantity=" + quantity+
//	    		", poId=" + poId+
//	    		", status=" + status+
//	    		"]";
//	}

}
