package bean;

public class BookBean {
  private String bid;//book id
  private String title;
  private String author;
  private int  price;
  private String category;
  
 public BookBean(String bid, String title, String author, int price, String category) {
	this.bid = bid;
	this.title = title;
	this.author = author;
	this.price = price;
	this.category = category;
}
 
 public BookBean(String bid, String title,  int price, String category) {
	this.bid = bid;
	this.title = title;

	this.price = price;
	this.category = category;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author=author;
}

@Override
public String toString() {
    return "BookBean [title=" + title + ", bid=" + bid + ", price=" + price +", category=" + category +", author=" + author +"]";
}
  
}