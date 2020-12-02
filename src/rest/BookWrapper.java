package rest;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import bean.BookBean;

public class BookWrapper {
	@XmlAttribute(name="bid")
	private String bid;
	
	@XmlElement(name="bookBean")
	private BookBean book;

	public BookWrapper(String bid, BookBean book) {
		this.bid = bid;
		this.book = book;
	}
	
	public BookWrapper() {
		// TODO Auto-generated constructor stub
	}
}
